package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Cloudware;
import com.guanshan.phoenix.enums.ImageTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.RancherService;
import io.rancher.Rancher;
import io.rancher.service.ContainerService;
import io.rancher.service.LoadBalancerServiceService;
import io.rancher.service.ServiceService;
import io.rancher.service.VolumeService;
import io.rancher.type.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RancherServiceImp implements RancherService {
    private static Logger log = Logger.getLogger(RancherServiceImp.class.getName());

    private Rancher rancher;

    private ServiceService serviceService;
    private ContainerService containerService;
    private LoadBalancerServiceService loadBalancerServiceService;
    private VolumeService volumeService;

    @Value("${rancher.cloudwareStackId}")
    private String cloudwareStackId;
    private String lbid;
    @Value("${rancher.cloudwareHubDomain}")
    private String cloudwareHubDomain;
    @Value("${rancher.wsUrl}")
    private String wsUrl;
    @Value("${rancher.webIdeBaseUrl}")
    private String webIdeBaseUrl;
    @Value("${rancher.vmLbSourcePort}")
    private int vmLbSourcePort;
    @Value("${rancher.vmLbTargetPort}")
    private int vmLbTargetPort;
    @Value("${rancher.notebookLbSourcePort}")
    private int notebookLbSourcePort;
    @Value("${rancher.notebookLbTargetPort}")
    private int notebookLbTargetPort;
    @Value("${rancher.ideLbSourcePort}")
    private int ideLbSourcePort;
    @Value("${rancher.ideLbTargetPort}")
    private int ideLbTargetPort;

    @Autowired
    public RancherServiceImp(Rancher rancher, @Value("${rancher.lbid}") String lbid) throws ApplicationErrorException, IOException {
        this.rancher = rancher;
        serviceService = rancher.type(ServiceService.class);
        containerService = rancher.type(ContainerService.class);
        loadBalancerServiceService = rancher.type(LoadBalancerServiceService.class);
        volumeService = rancher.type(VolumeService.class);
        this.lbid = lbid;

        initializeLBPorts();
    }

    @Override
    public void deleteCloudware(String serviceId, String pulsarId) throws IOException {

        if(pulsarId != null){
            ContainerService containerService = rancher.type(io.rancher.service.ContainerService.class);
            InstanceStop stop = new InstanceStop();
            stop.setRemove(true);
            containerService.stop(pulsarId, stop).execute();
        }

        if(serviceId != null) {
            ServiceService service = rancher.type(io.rancher.service.ServiceService.class);
            service.remove(serviceId).execute();
        }
    }

    @Override
    public Cloudware createCloudware(int userId, int imageType, String imageNameVersion) throws ApplicationErrorException, InterruptedException {
        if(imageType == ImageTypeEnum.NOTEBOOK.getCode()){
            return createNoteBookCloudware(userId, imageNameVersion);
        } else if (imageType == ImageTypeEnum.WEBIDE.getCode()){
            return createWebIDECloudware(imageNameVersion);
        } else if (imageType == ImageTypeEnum.CLOUDWARE.getCode()){
            return createVMCloudware(userId, imageNameVersion);
        } else {
            throw new ApplicationErrorException(ErrorCode.InvalidImageType);
        }
    }

    @Override
    public void createUserVolume(int userId) throws IOException {
        Volume volume = new Volume();
        volume.setDriver("rancher-nfs");
        volume.setName(((Integer)userId).toString());
        volumeService.create(volume).execute();
    }

    private Cloudware createVMCloudware(int userId, String imageNameVersion) throws ApplicationErrorException, InterruptedException {
        io.rancher.type.Service vmService = null;
        String pulsarId = null;
        boolean isSuccess = false;
        Cloudware cloudware;
        try {
            vmService = createVMService(userId, imageNameVersion);

            Response<io.rancher.type.Service> response = serviceService.create(vmService).execute();

            if(!response.isSuccess()){
                log.error("Failed to create VM service in rancher. Error message: " + response.errorBody().string());
                throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
            }

            vmService = response.body();
            String containerId = null;
            // get container id
            for(int i = 0; i < 10; i++) {
                vmService = serviceService.get(vmService.getId()).execute().body();
                if(vmService.getInstanceIds() != null && vmService.getInstanceIds().size() > 0){
                    containerId = vmService.getInstanceIds().get(0);
                    break;
                }
                Thread.sleep(1000);
            }

            if(containerId == null){
                log.error("Failed to get container id after creating VM service.");
                throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
            }

            // get host ID
            Container cloudwareContainer = containerService.get(containerId).execute().body();
            String hostId = cloudwareContainer.getHostId();

            // create pulsar
            Container pulsarContainer = createPulsarContainer(vmService.getName(), containerId, hostId);
            Response<Container> response2 = containerService.create(pulsarContainer).execute();

            if(!response2.isSuccess()){
                log.error("Failed to create pulsar container in rancher. Error message: " + response2.errorBody().string());
                throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
            }

            pulsarId = response2.body().getId();

            //Add rules to lb
            createLBRules(null,"/" + vmService.getName(), vmService.getId(), vmLbSourcePort, vmLbTargetPort);

            cloudware = new Cloudware();
            cloudware.setWebSocket(wsUrl + vmService.getName());
            cloudware.setServiceId(vmService.getId());
            cloudware.setServiceName(vmService.getName());
            cloudware.setPulsarId(pulsarId);
            isSuccess = true;
        }catch (IOException ex){
            log.error("Encountered IO exception while creating VM cloudware. Error message:" + ex.getMessage(), ex);
            throw new ApplicationErrorException(ErrorCode.GeneralError);
        }finally {
            //clean up all the services and containers
            if(!isSuccess){
                try{
                    assert vmService != null;
                    deleteCloudware(vmService.getId(), pulsarId);
                }catch (Exception ignored){}
            }
        }

        return cloudware;
    }

    private Cloudware createNoteBookCloudware(int userId, String imageNameVersion) throws ApplicationErrorException {
        io.rancher.type.Service nbService = null;
        boolean isSuccess = false;
        Cloudware cloudware;

        try {
            nbService = createNoteBookService(userId, imageNameVersion);
            Response<io.rancher.type.Service> response = serviceService.create(nbService).execute();

            if(!response.isSuccess()){
                log.error("Failed to create note book service in rancher. Error message: " + response.errorBody().string());
                throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
            }

            nbService = response.body();
            createLBRules(nbService.getName() + "." + cloudwareHubDomain, null, nbService.getId(), notebookLbSourcePort, notebookLbTargetPort);

            cloudware = new Cloudware();
            cloudware.setWebSocket(nbService.getName()+"." + cloudwareHubDomain + ":" + notebookLbSourcePort);
            cloudware.setServiceId(nbService.getId());
            cloudware.setServiceName(nbService.getName());
            isSuccess = true;
        }catch (IOException ex){
            log.error("Encountered IO exception while creating note book cloudware. Error message:" + ex.getMessage(), ex);
            throw new ApplicationErrorException(ErrorCode.GeneralError);
        }finally {
            //clean up all the services and containers
            if(!isSuccess){
                try{
                    assert nbService != null;
                    deleteCloudware(nbService.getId(), null);
                }catch (Exception ignored){}
            }
        }

        return cloudware;
    }

    private Cloudware createWebIDECloudware(String imageNameVersion) throws ApplicationErrorException {
        io.rancher.type.Service ideService = null;
        boolean isSuccess = false;
        Cloudware cloudware;

        try {
            ideService = createIDEService(imageNameVersion);
            Response<io.rancher.type.Service> response = serviceService.create(ideService).execute();

            if(!response.isSuccess()){
                log.error("Failed to create ide service in rancher. Error message: " + response.errorBody().string());
                throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
            }

            ideService = response.body();
            createLBRules(ideService.getName() + "." + cloudwareHubDomain, null, ideService.getId(), ideLbSourcePort, ideLbTargetPort);

            cloudware = new Cloudware();
            cloudware.setWebSocket(ideService.getName()+"."+ cloudwareHubDomain + ":"+ideLbSourcePort);
            cloudware.setServiceId(ideService.getId());
            cloudware.setServiceName(ideService.getName());
            isSuccess = true;
        }catch (IOException ex){
            log.error("Encountered IO exception while creating ide cloudware. Error message:" + ex.getMessage(), ex);
            throw new ApplicationErrorException(ErrorCode.GeneralError);
        }finally {
            //clean up all the services and containers
            if(!isSuccess){
                try{
                    assert ideService != null;
                    deleteCloudware(ideService.getId(), null);
                }catch (Exception ignored){}
            }
        }

        return cloudware;
    }

    private io.rancher.type.Service createCommonService(){
        String serviceName = RandomStringUtils.randomAlphanumeric(12);
        io.rancher.type.Service service = new io.rancher.type.Service();
        service.setScale(1);
        service.setAssignServiceIpAddress(false);
        service.setStartOnCreate(true);
        service.setStackId(cloudwareStackId);
        //service.setStackId()
        LaunchConfig launchConfig = new LaunchConfig();
        service.setLaunchConfig(launchConfig);
        launchConfig.setInstanceTriggeredStop("stop");
        launchConfig.setKind("container");
        launchConfig.setNetworkMode("managed");
        launchConfig.setPrivileged(false);
        launchConfig.setPublishAllPorts(false);
        launchConfig.setReadOnly(false);
        launchConfig.setStartOnCreate(true);
        launchConfig.setStdinOpen(true);
        launchConfig.setTty(true);
        launchConfig.setVcpu(1);
        Map<String, Object> labels = new HashMap<>();
        launchConfig.setLabels(labels);
        labels.put("io.rancher.scheduler.affinity:host_label", "cloudware=true");

        service.setName(serviceName);

        return service;
    }

    private io.rancher.type.Service createVMService(int userId, String imageNameVersion){
        io.rancher.type.Service vmService = createCommonService();
        //image UUUid
        vmService.getLaunchConfig().setImageUuid("docker:" + imageNameVersion);

        List<String> entryPoint = new ArrayList<>();
        entryPoint.add("startxfce4");
        vmService.getLaunchConfig().setEntryPoint(entryPoint);
        vmService.getLaunchConfig().setVcpu(4);

        //datavolumn
        List<String> dataVolumns = new ArrayList<>();
        dataVolumns.add(userId + ":/root/Desktop/myFile");
        vmService.getLaunchConfig().setDataVolumes(dataVolumns);

        return vmService;
    }

    private io.rancher.type.Service createNoteBookService(int userId, String imageNameVersion){
        io.rancher.type.Service nbService = createCommonService();
        nbService.getLaunchConfig().setImageUuid("docker:" + imageNameVersion);

        //datavolumn
        List<String> dataVolumns = new ArrayList<>();
        dataVolumns.add(userId + ":/home/jovyan");
        nbService.getLaunchConfig().setDataVolumes(dataVolumns);
        List<String> commands = new ArrayList<>();
        commands.add("start-notebook.sh");
        commands.add("--NotebookApp.token=''");
        nbService.getLaunchConfig().setCommand(commands);
        return nbService;
    }

    private io.rancher.type.Service createIDEService(String imageNameVersion){
        io.rancher.type.Service ideService = createCommonService();
        ideService.getLaunchConfig().setImageUuid("docker:" + imageNameVersion);

        //datavolumn
        List<String> dataVolumns = new ArrayList<>();
        dataVolumns.add("javaSrc:/srcData");
        ideService.getLaunchConfig().setDataVolumes(dataVolumns);
        return ideService;
    }

    private Container createPulsarContainer(String serviceName, String containerId, String hostId){
        Container container = new Container();
        container.setInstanceTriggeredStop("stop");
        container.setStartOnCreate(true);
        container.setPublishAllPorts(false);
        container.setPrivileged(false);
        container.setStdinOpen(true);
        container.setTty(true);
        container.setReadOnly(false);
        container.setRunInit(false);
        container.setNetworkMode("container");
        container.setRequestedHostId(hostId);
        RestartPolicy restartPolicy = new RestartPolicy();
        restartPolicy.setName("always");
        container.setRestartPolicy(restartPolicy);
        container.setImageUuid("docker:cloudwarelabs/pulsar");
        Map<String, Object> label = new HashMap<>();
        label.put("container_type", "cloudware");
        container.setLabels(label);
        container.setName(serviceName + "-pulsar");
        container.setNetworkContainerId(containerId);
        List<String> command = new ArrayList<>();
        command.add("pulsar");
        container.setCommand(command);

        return container;
    }

    private void createLBRules(String hostName, String path, String serviceId, int sourcePort, int targetPort) throws IOException, ApplicationErrorException {
        Response<LoadBalancerService> response = loadBalancerServiceService.get(lbid).execute();

        if(!response.isSuccess()){
            log.error("Failed to get load balance information. Error message: " + response.errorBody().string());
            throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
        }

        LoadBalancerService loadBalancerService = response.body();
        PortRule newRule = new PortRule();
        newRule.setProtocol("http");
        newRule.setHostname(hostName);
        newRule.setPath(path);
        newRule.setPriority(12);
        newRule.setServiceId(serviceId);
        newRule.setSourcePort(sourcePort);
        newRule.setTargetPort(targetPort);
        loadBalancerService.getLbConfig().getPortRules().add(newRule);
        Response response2 = loadBalancerServiceService.update(loadBalancerService.getId(), loadBalancerService).execute();

        if(!response2.isSuccess()){
            log.error("Failed to add rules to load balance. Error message: " + response2.errorBody().string());
            throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
        }
    }

    private void initializeLBPorts() throws ApplicationErrorException, IOException {
        Response<LoadBalancerService> response = loadBalancerServiceService.get(lbid).execute();

        if(!response.isSuccess()){
            log.error("Failed to get load balance information. Error message: " + response.errorBody().string());
            throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
        }

        LoadBalancerService loadBalancerService = response.body();

        List<String> ports = loadBalancerService.getLaunchConfig().getPorts();
        if(ports.indexOf("8888:8888/tcp") < 0){
            ports.add("8888:8888/tcp");
        }
        if(ports.indexOf("83:83/tcp") < 0){
            ports.add("83:83/tcp");
        }
        if(ports.indexOf("84:84/tcp") < 0){
            ports.add("84:84/tcp");
        }
        Response response2 = loadBalancerServiceService.update(loadBalancerService.getId(), loadBalancerService).execute();

        if(!response2.isSuccess()){
            log.error("Failed to add rules to load balance. Error message: " + response2.errorBody().string());
            throw new ApplicationErrorException(ErrorCode.FailedToCreateCloudware);
        }
    }
}
