package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.service.StudentService;
import com.guanshan.phoenix.webdomain.ResStudentClassList;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "选课列表", notes = "列出所有该学生的选课")
    @GetMapping(value = "course/all/{studentId}")
    public ResStudentClassList getAllStudentCourses(@PathVariable int studentId) {
        return studentService.getAllStudentClassInfoById(studentId);
    }
}
