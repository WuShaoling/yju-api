package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.webdomain.response.ResHotCourseList;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "获取热门课程信息", notes = "")
    @GetMapping(value = "hotCourses/all")
    public ResponseMessage<ResHotCourseList> getHotCourses() {
        return new ResponseMessage.Success<>(courseService.getHotCourses());
    }
}
