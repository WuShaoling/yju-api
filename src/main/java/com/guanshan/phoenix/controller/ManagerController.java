package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.service.TermService;
import com.guanshan.phoenix.webdomain.ReqUpdateTeacher;
import com.guanshan.phoenix.webdomain.ResCourseList;
import com.guanshan.phoenix.webdomain.ResSemesterList;
import com.guanshan.phoenix.webdomain.ResTeacherList;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class ManagerController {

    @Autowired
    private TermService termService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "获取所有老师信息", notes = "")
    @GetMapping(value = "teacher/all")
    public ResponseMessage<ResTeacherList> getAllTeachers() throws ApplicationErrorException {
        return new ResponseMessage.Success<>(teacherService.getAllTeacherList());
    }

    @ApiOperation(value = "更新老师信息", notes = "")
    @PostMapping(value = "teacher/updation")
    public ResponseMessage updateTeacher(@RequestBody ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {
        teacherService.updateTeacher(reqUpdateTeacher);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "删除老师", notes = "")
    @PostMapping(value = "teacher/{teacherId}/deletion")
    public ResponseMessage deleteTeacher(@PathVariable int teacherId) throws ApplicationErrorException {
        teacherService.deleteTeacherByTeacherId(teacherId);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "获取所有学期信息", notes = "")
    @GetMapping(value = "semester/all")
    public ResponseMessage<ResSemesterList> getAllSemesters() throws ApplicationErrorException {
        return new ResponseMessage.Success<>(termService.getAllTerms());
    }

    @ApiOperation(value = "新增学期", notes = "")
    @PostMapping(value = "semester/creation")
    public ResponseMessage createSemester(@RequestBody Term term) throws ApplicationErrorException {
        termService.create(term);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "更新学期", notes = "")
    @PostMapping(value = "semester/updation")
    public ResponseMessage updateSemester(@RequestBody Term term) throws ApplicationErrorException {
        termService.update(term);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "删除学期", notes = "")
    @PostMapping(value = "semester/{semesterId}/deletion")
    public ResponseMessage deleteSemester(@PathVariable int semesterId) throws ApplicationErrorException {
        termService.delete(semesterId);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "获取课程信息", notes = "")
    @GetMapping(value = "course/all")
    public ResponseMessage<ResCourseList> getAllCourses() throws ApplicationErrorException {
        return new ResponseMessage.Success<>(courseService.getAllCourses());
    }

    @ApiOperation(value = "创建课程信息", notes = "")
    @PostMapping(value = "course/creation")
    public ResponseMessage createCourse(@RequestBody Course course) throws ApplicationErrorException {
        courseService.createCourse(course);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "更新课程信息", notes = "")
    @PostMapping(value = "course/updation")
    public ResponseMessage updateCourse(@RequestBody Course course) throws ApplicationErrorException {
        courseService.updateCourse(course);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "删除课程信息", notes = "")
    @PostMapping(value = "course/{courseId}/deletion")
    public ResponseMessage deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
        return new ResponseMessage.Success();
    }
}
