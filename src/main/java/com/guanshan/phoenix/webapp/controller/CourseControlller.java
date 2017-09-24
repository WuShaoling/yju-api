package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.dao.entity.Course;
import com.guanshan.phoenix.webapp.service.CourseService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "course")
public class CourseControlller {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "获取所有课程", notes = "获取所有课程")
    @GetMapping(value = "all")
    public ResponseMessage<List<Course>> getAllCourses() {
        try {
            return new ResponseMessage<>(Const.SUCCESS,
                    "success",
                    courseService.getAllCourse());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage<>(Const.FAIL, "fail");
        }
    }
}
