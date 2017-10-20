package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.*;
import com.guanshan.phoenix.webdomain.*;
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
    private HomeworkService homeworkService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassService classService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private StudentService studentService;

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
    @PostMapping(value = "teacher/deletion")
    public ResponseMessage deleteTeacher(@RequestParam int teacherId) throws ApplicationErrorException {
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
    @PostMapping(value = "semester/deletion")
    public ResponseMessage deleteSemester(@RequestParam int semesterId) throws ApplicationErrorException {
        termService.delete(semesterId);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "删除作业", notes = "")
    @PostMapping(value = "class/homework/deletion")
    public ResponseMessage deleteHomework(@RequestParam("homeworkId") int homeworkId) throws ApplicationErrorException {
        homeworkService.deleteHomework(homeworkId);
        return new ResponseMessage.Success();
    }


    @ApiOperation(value = "更新作业", notes = "")
    @PostMapping(value = "class/homework/updation")
    public ResponseMessage updateHomework(@RequestBody ReqUpdateHomework reqUpdateHomework) throws ApplicationErrorException {
        homeworkService.updateHomework(reqUpdateHomework);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "添加作业", notes = "")
    @PostMapping(value = "class/homework/creation")
    public ResponseMessage updateHomework(@RequestBody ReqCreateHomework reqUpdateHomework) throws ApplicationErrorException {
        homeworkService.createHomework(reqUpdateHomework);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "获取班级作业", notes = "")
    @GetMapping(value = "/class/{classId}/homework")
    public ResponseMessage<ResClassHomework> getClassHomework(@PathVariable("classId") int classId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(homeworkService.getClassHomework(classId));
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
    @PostMapping(value = "course/deletion")
    public ResponseMessage deleteCourse(@RequestParam int id){
        courseService.deleteCourse(id);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "删除班级学生", notes = "删除班级里的一个学生")
    @PostMapping(value = "/class/student/deletion")
    public ResponseMessage deleteClassStudent(@RequestBody ReqDeleteClassStudent reqDeleteClassStudent) throws ApplicationErrorException {
        classService.deleteClassStudent(reqDeleteClassStudent);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "重置密码", notes = "")
    @PostMapping(value = "/password/resetion")
    public ResponseMessage resetPassword(@RequestParam("userId") int userId) throws ApplicationErrorException {
        managerService.resetPassword(userId);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "更新学生信息", notes = "")
    @PostMapping(value = "/class/student/updation")
    public ResponseMessage updateStudentInfo(ReqUpdateStudent reqUpdateStudent) throws ApplicationErrorException {
        studentService.updateStudentInfo(reqUpdateStudent);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "添加班级学生", notes = "")
    @PostMapping(value = "/class/student/creation")
    public ResponseMessage addClassStudent(@RequestParam("classId") int classId, @RequestParam("studentId") int studentId) throws ApplicationErrorException {
        classService.addClassStudent(classId, studentId);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "获取班级所有学生信息", notes = "")
    @PostMapping(value = "class/{classId}/students/all")
    public ResponseMessage<ResClassStudents> getAllClassStudentInfo(@PathVariable("classId") int classId) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(classService.getAllClassStudentInfo(classId));
    }
    
}
