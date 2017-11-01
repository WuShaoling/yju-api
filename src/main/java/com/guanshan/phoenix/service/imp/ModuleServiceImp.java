package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ExperimentService;
import com.guanshan.phoenix.service.HomeworkService;
import com.guanshan.phoenix.service.ModuleService;
import com.guanshan.phoenix.webdomain.request.*;
import com.guanshan.phoenix.webdomain.response.ResModuleId;
import com.guanshan.phoenix.webdomain.response.ResModuleImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImp implements ModuleService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private ModuleResourceMapper moduleResourceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private HomeworkService homeworkService;

    @Override
    public ResModuleId createModule(Module module) throws ApplicationErrorException {
        Course course = courseMapper.selectByPrimaryKey(module.getCourseId());

        if(course == null){
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        moduleMapper.insert(module);
        return new ResModuleId(module.getId());
    }

    @Override
    public void deleteModule(ReqDeleteModule reqDeleteModule) throws ApplicationErrorException {
        int moduleId = reqDeleteModule.getModuleId();

        if(moduleMapper.selectByPrimaryKey(moduleId) == null){
            throw new ApplicationErrorException(ErrorCode.ModuleNotExists);
        }

//        if(experimentMapper.isModuleUsedByExperiment(moduleId)){
//            throw new ApplicationErrorException(ErrorCode.ModuleUsedByExperiment);
//        }
//
//        if(homeworkMapper.isModuleUsedByHomework(moduleId)){
//            throw new ApplicationErrorException(ErrorCode.ModuleUsedByHomework);
//        }

        for(Experiment experiment : experimentMapper.selectByModuleId(moduleId)){
            experimentService.deleteExperiment(new ReqDeleteExperiment(experiment.getId()));
        }

        for(Homework homework : homeworkMapper.selectByModuleId(moduleId)){
            homeworkService.deleteHomework(new ReqDeleteHomework(homework.getId()));
        }

        for (ModuleResource moduleResource : moduleResourceMapper.selectByModuleId(moduleId)){
            moduleResourceMapper.deleteByPrimaryKey(moduleResource.getId());
            resourceMapper.deleteByPrimaryKey(moduleResource.getResourceId());
        }

        moduleMapper.deleteByPrimaryKey(moduleId);
    }

    @Override
    public ResModuleImages getModuleImageUrls(int moduleId) {
        ResModuleImages resModuleImages = new ResModuleImages();
        List<ResModuleImages.ResModuleImage> urlList = new ArrayList<>();

        List<ModuleResource> moduleResourceList = moduleResourceMapper.selectByModuleId(moduleId);
        for (ModuleResource moduleResource : moduleResourceList) {
            Resource resource = resourceMapper.selectByPrimaryKey(moduleResource.getResourceId());
            ResModuleImages.ResModuleImage moduleImage = new ResModuleImages.ResModuleImage(
                    resource.getId(),
                    resource.getName(),
                    resource.getUrl(),
                    resource.getWidth(),
                    resource.getHeight()
            );
            urlList.add(moduleImage);
        }
        resModuleImages.setImageList(urlList);

        return resModuleImages;
    }

    @Override
    public void addModuleResource(ReqAddModuleResource reqAddModuleResource) throws ApplicationErrorException {
        if(moduleMapper.selectByPrimaryKey(reqAddModuleResource.getModuleId()) == null){
            throw new ApplicationErrorException(ErrorCode.ModuleNotExists);
        }

        Resource resource = new Resource(
                reqAddModuleResource.getName(),
                reqAddModuleResource.getImageUrl(),
                reqAddModuleResource.getWidth(),
                reqAddModuleResource.getHeight()
        );

        resourceMapper.insert(resource);
        ModuleResource moduleResource = new ModuleResource(
                reqAddModuleResource.getModuleId(),
                resource.getId(),
                ResourceTypeEnum.IMAGE.getCode()
        );
        moduleResourceMapper.insert(moduleResource);
    }

    @Override
    public void deleteModuleResource(ReqDeleteModuleResource reqDeleteModuleResource) throws ApplicationErrorException {
        ModuleResource moduleResource = moduleResourceMapper.selectByModuleIdAndResourceId(
                reqDeleteModuleResource.getModuleId(),
                reqDeleteModuleResource.getResourceId()
        );
        if(moduleResource == null){
            throw new ApplicationErrorException(ErrorCode.ModuleResourceNotFound);
        }

        moduleResourceMapper.deleteByPrimaryKey(moduleResource.getId());
        resourceMapper.deleteByPrimaryKey(moduleResource.getResourceId());
    }
}
