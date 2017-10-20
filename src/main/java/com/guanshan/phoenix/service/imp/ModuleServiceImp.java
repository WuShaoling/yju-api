package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Module;
import com.guanshan.phoenix.dao.mapper.CourseMapper;
import com.guanshan.phoenix.dao.mapper.ModuleMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImp implements ModuleService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ModuleMapper moduleMapper;

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
        //todo: delete all homework, student_homework and cloudware
        moduleMapper.deleteByPrimaryKey(moduleId);
    }
}
