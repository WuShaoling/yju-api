package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.service.TermService;
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

    @ApiOperation(value = "获取所有老师信息", notes = "")
    @GetMapping(value = "teacher/all")
    public ResponseMessage<ResTeacherList> getAllTeachers() throws ApplicationErrorException {
        return new ResponseMessage.Success<>(teacherService.getAllTeacherList());
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
}
