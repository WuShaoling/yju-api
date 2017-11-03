package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.ExperimentService;
import com.guanshan.phoenix.service.StudentService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.response.ResCommonCourseDetail;
import com.guanshan.phoenix.webdomain.response.ResCourseModuleExperiments;
import com.guanshan.phoenix.webdomain.response.ResHotCourseList;
import com.guanshan.phoenix.webdomain.response.ResStatistics;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ExperimentService experimentService;

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

    @ApiOperation(value = "获取网站统计信息", notes = "包含学生，课程，实验，老师数量")
    @GetMapping(value = "statistics")
    public ResponseMessage<ResStatistics> getStatistics() throws ApplicationErrorException {
        ResStatistics statistics = new ResStatistics(
                studentService.getCount(),
                teacherService.getCount(),
                courseService.getCount(),
                experimentService.getCount()
        );
        return new ResponseMessage.Success<>(statistics);
    }
}
