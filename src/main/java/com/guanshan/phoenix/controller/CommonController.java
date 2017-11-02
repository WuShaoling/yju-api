package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.webdomain.response.ResCommonCourseDetail;
import com.guanshan.phoenix.webdomain.response.ResCourseModuleExperiments;
import com.guanshan.phoenix.webdomain.response.ResHotCourseList;
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
    public ResponseMessage<ResHotCourseList> getHotCourses() {
        return new ResponseMessage.Success<>(courseService.getHotCourses());
    }

    @ApiOperation(value = "获取课程实验信息", notes = "")
    @GetMapping(value = "course/{courseId}/experiments")
    public ResponseMessage<ResCourseModuleExperiments> getCourseExperiments(@PathVariable int courseId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getCourseModuleExperiments(courseId));
    }

    @ApiOperation(value = "获取课程相关信息", notes = "包含老师姓名，班级数以及班级人数")
    @GetMapping(value = "course/{courseId}/detail")
    public ResponseMessage<ResCommonCourseDetail> getCourseCommonDetail(@PathVariable int courseId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getCommonCourseDetail(courseId));
    }
}
