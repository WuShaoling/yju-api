package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.webdomain.RespTeacherCourse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "获取老师的课程情况")
    @GetMapping("/teacher/{teacherId}/course/all")
    public ResponseMessage<RespTeacherCourse> getTeacherCourse(@PathVariable("teacherId") int teacherId) {
        return new ResponseMessage.Success<>(teacherService.getTeacherCourse(teacherId));
    }
}
