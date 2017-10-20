package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.HomeworkService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.*;
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
        return new ResponseMessage.Success<>(teacherService.getAllTeacherClassInfoByUserId(teacherId));
    }

    @ApiOperation(value = "课程详情", notes = "列出所有该课程的课时以及属于这些课时的所有实验内容")
    @GetMapping(value = "course/{classId}")
    public ResponseMessage<ResCourseModuleExperiments> getCourseExperiments(@PathVariable int classId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getCourseModuleExperiments(classId));
    }

    @ApiOperation(value = "所有学生作业详情", notes = "列出该课时所有学生作业的完成情况")
    @GetMapping(value = "course/homework/{moduleId}")
    public ResponseMessage<ResHomeworkSubmissionList> getAllHomeworkSubmissionByModuleId(@PathVariable int moduleId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(homeworkService.getAllHomeworkSubmissionByModuleId(moduleId));
    }

    @ApiOperation(value = "单个学生作业详情", notes = "")
    @GetMapping(value = "course/homework/{studentHomeworkId}")
    public ResponseMessage<ResStudentHomeworkDetail> getStudentHomeworkDetailById(@PathVariable int studentHomeworkId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(homeworkService.getStudentHomeworkDetailById(studentHomeworkId));
    }

    @ApiOperation(value = "批改作业", notes = "")
    @PostMapping(value = "course/homework/grade")
    public ResponseMessage gradeHomework(@RequestBody ReqHomeworkGrade homeworkGrade) throws ApplicationErrorException {
        teacherService.gradeHomework(homeworkGrade);
        return new ResponseMessage.Success();
    }
}
