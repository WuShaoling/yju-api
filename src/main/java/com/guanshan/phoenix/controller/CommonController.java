package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.webdomain.response.ResClassStudents;
import com.guanshan.phoenix.webdomain.response.ResHotCourseDetail;
import com.guanshan.phoenix.webdomain.response.ResTeacherList;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "获取热门课程信息", notes = "")
    @GetMapping(value = "hotCourses/all")
    public ResponseMessage<List<ResHotCourseDetail>> getHotCourses() {
        return new ResponseMessage.Success<>(courseService.getHotCourses());
    }
}
