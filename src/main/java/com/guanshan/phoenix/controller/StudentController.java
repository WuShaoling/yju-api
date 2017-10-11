package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.service.StudentService;
import com.guanshan.phoenix.shared.util.codec.Const;
import com.guanshan.phoenix.shared.util.codec.ResponseMessage;
import com.guanshan.phoenix.webdomain.ReqPasswdModify;
import com.guanshan.phoenix.webdomain.RespStudentCourse;
import com.guanshan.phoenix.webdomain.RespStudentCourseDetail;
import com.guanshan.phoenix.webdomain.RespStudentHomework;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "修改学生登陆密码")
    @PostMapping("/password/modification")
    public ResponseMessage updatePassword(@RequestBody ReqPasswdModify reqPasswdModify) {
        if (studentService.updatePassword(reqPasswdModify) == 0) {
            return new ResponseMessage(Const.SUCCESS, "success");
        } else {
            return new ResponseMessage(Const.FAIL, "fail");
        }
    }

    @ApiOperation(value = "获取学生选课")
    @GetMapping("/course/all/{studentId}")
    public ResponseMessage<RespStudentCourse> getStudentCourse(@PathVariable("studentId") int studentId) {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                studentService.getStudentCourses(studentId));
    }

    @ApiOperation(value = "获取学生课程详情")
    @GetMapping("/course/{courseId}/detail")
    public ResponseMessage<RespStudentCourseDetail> getCourseDetail(@PathVariable("courseId") int courseId) {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                studentService.getCourseDetail(courseId));
    }

    @ApiOperation(value = "获取学生作业情况")
    @GetMapping("/course/{courseId}/homework")
    public ResponseMessage<RespStudentHomework> getStudentHomework(@PathVariable("courseId") int courseId) {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                studentService.getStudentHomework(courseId));
    }
}

