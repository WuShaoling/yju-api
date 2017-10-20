package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Module;
import com.guanshan.phoenix.dao.entity.ModuleResource;
import com.guanshan.phoenix.dao.entity.Resource;
import com.guanshan.phoenix.dao.mapper.CourseMapper;
import com.guanshan.phoenix.dao.mapper.ModuleMapper;
import com.guanshan.phoenix.dao.mapper.ModuleResourceMapper;
import com.guanshan.phoenix.dao.mapper.ResourceMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ModuleService;
import com.guanshan.phoenix.webdomain.ResModuleImages;
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

    @Override
    public void createModule(Module module) throws ApplicationErrorException {
        Course course = courseMapper.selectByPrimaryKey(module.getCourseId());

        if(course == null){
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        moduleMapper.insert(module);
    }

    @Override
    public void deleteModule(int moduleId) {
        moduleMapper.deleteByPrimaryKey(moduleId);
    }

    @Override
    public ResModuleImages getModuleImageUrls(int moduleId) {
        ResModuleImages resModuleImages = new ResModuleImages();
        List<String> urlList = new ArrayList<>();

        List<ModuleResource> moduleResourceList = moduleResourceMapper.selectByModuleId(moduleId);
        for (ModuleResource moduleResource : moduleResourceList) {
            Resource resource = resourceMapper.selectByPrimaryKey(moduleResource.getResourceId());
            urlList.add(resource.getUrl());
        }
        resModuleImages.setImageList(urlList);

        return resModuleImages;
    }
}
