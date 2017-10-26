package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.dao.entity.Cloudware;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.*;
import com.guanshan.phoenix.webdomain.request.ReqHomeworkSubmission;
import com.guanshan.phoenix.webdomain.request.ReqStudentExperimentCloudware;
import com.guanshan.phoenix.webdomain.request.ReqStudentHomeworkCloudware;
import com.guanshan.phoenix.webdomain.response.*;
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

    @Autowired
    private StudentExperimentService studentExperimentService;

    @Autowired
    private ExperimentService experimentService;

    @ApiOperation(value = "选课列表", notes = "列出所有该学生的班级列表")
    @GetMapping(value = "course/all/{studentId}")
    public ResponseMessage<ResStudentClassList> getAllStudentCourses(@PathVariable int studentId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(studentService.getAllStudentClassInfoByUserId(studentId));
    }

    @ApiOperation(value = "课程详情", notes = "列出所有该课程的课时以及属于这些课时的所有实验内容")
    @GetMapping(value = "course/{classId}/detail")
    public ResponseMessage<ResCourseModuleExperiments> getCourseExperiments(@PathVariable int classId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getClassModuleExperiments(classId));
    }

    @ApiOperation(value = "班级详情", notes = "列出该班级所属课程的的课时以及属于这些课时的所有作业")
    @GetMapping(value = "course/{classId}/{studentId}/homework/")
    public ResponseMessage<ResCourseHomeworks> getCourseHomeworks(@PathVariable int classId, @PathVariable int studentId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getCourseHomeworks(classId, studentId));
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

    @ApiOperation(value = "获取学生作业云件", notes = "")
    @GetMapping(value = "homework/{homeworkId}/{studentId}/cloudware")
    public ResponseMessage<Cloudware> getStudentHomeworkCloudware(@PathVariable int homeworkId, @PathVariable int studentId) throws ApplicationErrorException {
        return new ResponseMessage.Success(studentHomeworkService.getStudentHomeworkCloudware(homeworkId, studentId));
    }

    @ApiOperation(value = "获取学生实验云件", notes = "")
    @GetMapping(value = "experiment/{experimentId}/{studentId}/cloudware")
    public ResponseMessage<Cloudware> getStudentExperimentCloudware(@PathVariable int experimentId, @PathVariable int studentId) throws ApplicationErrorException {
        return new ResponseMessage.Success(studentExperimentService.getStudentExperimentCloudware(experimentId, studentId));
    }

    @ApiOperation(value = "创建学生作业以及云件", notes = "")
    @PostMapping(value = "homework/creation")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseMessage createStudentHomeworkCloudware(@RequestBody ReqStudentHomeworkCloudware reqStudentHomeworkCloudware) throws ApplicationErrorException {
        studentHomeworkService.createStudentHomeworkCloudware(reqStudentHomeworkCloudware);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "创建学生实验以及云件", notes = "")
    @PostMapping(value = "experiment/creation")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseMessage createStudentExperimentCloudware(@RequestBody ReqStudentExperimentCloudware reqStudentExperimentCloudware) throws ApplicationErrorException {
        studentExperimentService.createStudentExperimentCloudware(reqStudentExperimentCloudware);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "获取实验信息", notes = "")
    @GetMapping(value = "experiment/{experimentId}")
    public ResponseMessage<ResExperimentInfo> getExperiment(@PathVariable int experimentId) throws ApplicationErrorException {
        return new ResponseMessage.Success(experimentService.getExperiment(experimentId));
    }
}
