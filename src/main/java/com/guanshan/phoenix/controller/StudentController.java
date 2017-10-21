package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.HomeworkService;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.service.StudentService;
import com.guanshan.phoenix.webdomain.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private StudentHomeworkService studentHomeworkService;

    @ApiOperation(value = "选课列表", notes = "列出所有该学生的班级列表")
    @GetMapping(value = "course/all/{studentId}")
    public ResponseMessage<ResStudentClassList> getAllStudentCourses(@PathVariable int studentId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(studentService.getAllStudentClassInfoByUserId(studentId));
    }

    @ApiOperation(value = "课程详情", notes = "列出所有该课程的课时以及属于这些课时的所有实验内容")
    @GetMapping(value = "course/{classId}/detail")
    public ResponseMessage<ResCourseModuleExperiments> getCourseExperiments(@PathVariable int classId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getCourseModuleExperiments(classId));
    }

    @ApiOperation(value = "班级详情", notes = "列出该班级所属课程的的课时以及属于这些课时的所有作业")
    @GetMapping(value = "course/{classId}/homework")
    public ResponseMessage<ResCourseHomeworks> getCourseHomeworks(@PathVariable int classId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getCourseHomeworks(classId));
    }

    @ApiOperation(value = "作业详情", notes = "")
    @GetMapping(value = "homework/{homeworkId}")
    public ResponseMessage<ResHomeworkDetail> getHomeworkDetail(@PathVariable int homeworkId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(homeworkService.getHomeworkDetail(homeworkId));
    }

    @ApiOperation(value = "提交作业", notes = "")
    @PostMapping(value = "homework/submission")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseMessage submitHomeWork(@RequestBody ReqHomeworkSubmission homeworkSubmission) throws ApplicationErrorException {
        studentHomeworkService.submitStudentHomework(homeworkSubmission);
        return new ResponseMessage.Success();
    }
}
