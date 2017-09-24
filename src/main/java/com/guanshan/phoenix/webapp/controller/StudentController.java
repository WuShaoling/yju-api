package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.dao.entity.Course;
import com.guanshan.phoenix.webapp.service.StudentService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "获取学生选课", notes = "根据学生ID返回学生的选课列表")
    @GetMapping(value = "courses")
    public ResponseMessage<List<Course>> getAllCourses(@RequestParam("id") Integer id) {
        try {
            return new ResponseMessage<>(Const.SUCCESS,
                    "success",
                    studentService.getAllCourses(id)) ;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage<>(Const.FAIL, "fail");
        }
    }
}
