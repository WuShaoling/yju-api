package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Cloudware;
import com.guanshan.phoenix.dao.entity.StudentExperiment;
import com.guanshan.phoenix.dao.mapper.CloudwareMapper;
import com.guanshan.phoenix.dao.mapper.ExperimentMapper;
import com.guanshan.phoenix.dao.mapper.StudentExperimentMapper;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.CloudwareService;
import com.guanshan.phoenix.service.RancherService;
import com.guanshan.phoenix.service.StudentExperimentService;
import com.guanshan.phoenix.webdomain.request.ReqStudentExperimentCloudware;
import com.guanshan.phoenix.webdomain.response.ResStudentLastExperiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentExperimentServiceImp implements StudentExperimentService {

    @Autowired
    private StudentExperimentMapper studentExperimentMapper;

    @Autowired
    private CloudwareMapper cloudwareMapper;

    @Autowired
    private CloudwareService cloudwareService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private RancherService rancherService;

    @Override
    public Cloudware getStudentExperimentCloudware(int experimentId, int studentId) throws ApplicationErrorException {
        StudentExperiment studentExperiment = studentExperimentMapper.selectByStudentIdAndExperimentId(studentId, experimentId);
        if(studentExperiment == null){
            throw new ApplicationErrorException(ErrorCode.StudentExperimentNotFound);
        }
        if(studentExperiment.getCloudwareId() == null){
            throw new ApplicationErrorException(ErrorCode.CloudwareNotExist);
        }

        return cloudwareMapper.selectByPrimaryKey(studentExperiment.getCloudwareId());
    }

    @Override
    public Cloudware createStudentExperimentCloudware(ReqStudentExperimentCloudware reqStudentExperimentCloudware) throws ApplicationErrorException, InterruptedException {
        validateStudentExperiment(
                reqStudentExperimentCloudware.getStudentId(),
                reqStudentExperimentCloudware.getExperimentId()
        );

        Cloudware cloudware = rancherService.createCloudware(reqStudentExperimentCloudware.getStudentId(),
                                                             reqStudentExperimentCloudware.getCloudwareType());

        StudentExperiment studentExperiment =
                studentExperimentMapper.selectByStudentIdAndExperimentId(reqStudentExperimentCloudware.getStudentId(),
                        reqStudentExperimentCloudware.getExperimentId());

        cloudwareMapper.insert(cloudware);

        if(studentExperiment != null){
            studentExperiment.setCloudwareId(cloudware.getId());
            studentExperimentMapper.updateByPrimaryKey(studentExperiment);
        } else {

            studentExperiment = new StudentExperiment(
                    reqStudentExperimentCloudware.getStudentId(),
                    reqStudentExperimentCloudware.getExperimentId(),
                    cloudware.getId()
            );
            studentExperimentMapper.insert(studentExperiment);
        }

        return cloudware;
    }

    @Override
    public void deleteStudentExperiment(int studentExperimentId) throws ApplicationErrorException {

        StudentExperiment studentExperiment = studentExperimentMapper.selectByPrimaryKey(studentExperimentId);
        if(studentExperiment == null){
            throw new ApplicationErrorException(ErrorCode.StudentExperimentNotFound);
        }

        studentExperimentMapper.deleteByPrimaryKey(studentExperimentId);
        if(studentExperiment.getCloudwareId() != null){
            cloudwareService.deleteCloudware(studentExperiment.getCloudwareId());
        }
    }

    @Override
    public void deleteStudentExperiment(int experimentId, int studentId) throws ApplicationErrorException {
        StudentExperiment studentExperiment =
                studentExperimentMapper.selectByStudentIdAndExperimentId(studentId, experimentId);

        if(studentExperiment == null){
            throw new ApplicationErrorException(ErrorCode.StudentExperimentNotFound);
        }

        studentExperimentMapper.deleteByPrimaryKey(studentExperiment.getId());
        if(studentExperiment.getCloudwareId() != null){
            cloudwareService.deleteCloudware(studentExperiment.getCloudwareId());
        }
    }

    @Override
    public ResStudentLastExperiment getStudentLastExperiment(int studentId) throws ApplicationErrorException {
        if(userMapper.selectByPrimaryKey(studentId) == null){
            throw new ApplicationErrorException(ErrorCode.UserNotExist);
        }

        Map lastExperimentInfo = studentExperimentMapper.selectLastExperimentByUserId(studentId);
        if(lastExperimentInfo == null){
            return new ResStudentLastExperiment();
        }

        return new ResStudentLastExperiment(
                (int)lastExperimentInfo.get("experimentId"),
                (String)lastExperimentInfo.get("experimentName"),
                (String)lastExperimentInfo.get("moduleName")
        );
    }

    private void validateStudentExperiment(int studentId, int experimentId) throws ApplicationErrorException {
        if(userMapper.selectByPrimaryKey(studentId) == null){
            throw new ApplicationErrorException(ErrorCode.UserNotExist);
        }

        if(experimentMapper.selectByPrimaryKey(experimentId) == null){
            throw new ApplicationErrorException(ErrorCode.ExperimentNotFound);
        }
    }
}
