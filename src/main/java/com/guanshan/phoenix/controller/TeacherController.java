package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.ResTeacherClassList;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "选课列表", notes = "列出所有该教师的班级列表")
    @GetMapping(value = "{teacherId}/course/all")
    public ResponseMessage<ResTeacherClassList> getAllTeacherCourses(@PathVariable int teacherId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(teacherService.getAllTeacherClassInfoById(teacherId));
    }
}
