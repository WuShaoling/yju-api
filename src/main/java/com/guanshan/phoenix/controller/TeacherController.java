package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.HomeworkService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.ResCourseExperiments;
import com.guanshan.phoenix.webdomain.ResHomeworkSubmissionList;
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

    @Autowired
    private CourseService courseService;

    @Autowired
    private HomeworkService homeworkService;

    @ApiOperation(value = "选课列表", notes = "列出所有该教师的班级列表")
    @GetMapping(value = "{teacherId}/course/all")
    public ResponseMessage<ResTeacherClassList> getAllTeacherCourses(@PathVariable int teacherId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(teacherService.getAllTeacherClassInfoById(teacherId));
    }

    @ApiOperation(value = "课程详情", notes = "列出所有该课程的课时以及属于这些课时的所有实验内容")
    @GetMapping(value = "course/{classId}")
    public ResponseMessage<ResCourseExperiments> getCourseExperiments(@PathVariable int classId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getCourseExperiments(classId));
    }

    @ApiOperation(value = "作业详情", notes = "列出该课时所有学生作业的完成情况")
    @GetMapping(value = "course/homework/{moduleId}")
    public ResponseMessage<ResHomeworkSubmissionList> getAllHomeworkSubmissionByModuleId(@PathVariable int moduleId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(homeworkService.getAllHomeworkSubmissionByModuleId(moduleId));
    }
}
