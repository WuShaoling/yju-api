package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.CourseResource;
import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseResourceMapper courseResourceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    public Course getCourseById(int courseID) {

        Course course = courseMapper.selectByPrimaryKey(courseID);
        course.setImageUrl(this.getImageUrl(courseID));
        return course;
    }

    private String getImageUrl(int courseID){
        CourseResource courseResource =
                courseResourceMapper.selectByPrimaryKeyAndType(courseID, ResourceTypeEnum.IMAGE.getCode());

        if(courseResource == null){
            return "";
        }

        return resourceMapper.selectByPrimaryKey(courseResource.getResourceId()).getUrl();
    }
}
