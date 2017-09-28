package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.service.CourseService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import com.guanshan.phoenix.webapp.webdomain.WebCourse;
import com.guanshan.phoenix.webapp.webdomain.WebPeriodDetail;
import com.guanshan.phoenix.webapp.webdomain.WebStudentCourse;
import com.guanshan.phoenix.webapp.webdomain.WebTerm;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "获取全部课程基础信息")
    @GetMapping("all")
    public ResponseMessage<List<WebCourse>> getAllCourseInfo() {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                courseService.getAllCourseInfo());
    }

    @ApiOperation(value = "获取课时详细信息", notes = "包含所有课时及所含实验")
    @GetMapping("period/all")
    public ResponseMessage<List<WebPeriodDetail>> getAllPeriodDetailInfo(@RequestParam("courseId") int courseId) {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                courseService.getAllPeriodDetailInfo(courseId));
    }

    @ApiOperation(value = "获取所有学期信息")
    @GetMapping("term/all")
    public ResponseMessage<List<WebTerm>> getAllTermInfo() {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                courseService.getALlTermInfo());
    }

    @ApiOperation(value = "获取学生的选课信息")
    @GetMapping("student")
    public ResponseMessage<WebStudentCourse> getStudentCourseInfo(@RequestParam("studentId") int studentId) {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                courseService.getStudentCourseInfo(studentId));
    }

}
