package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.service.ManagerService;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.webdomain.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @ApiOperation(value = "获取所有教师信息")
    @GetMapping("/teacher/all")
    public ResponseMessage<RespTeacherInfo> getAllTeacherInfo() {
        return new ResponseMessage.Success<>(managerService.getAllTeacherInfo());
    }

    @ApiOperation(value = "添加教师信息")
    @PostMapping("/teacher/creation")
    public ResponseMessage addTeacherInfo(@RequestBody ReqAddTeacher reqAddTeacher) {
        managerService.addTeacherInfo(reqAddTeacher);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "更新教师信息")
    @PostMapping("/teacher/updation")
    public ResponseMessage updateTeacherInfo(@RequestBody ReqUpdateTeacher reqUpdateTeacher) {
        managerService.updateTeacherInfo(reqUpdateTeacher);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "重置教师密码")
    @PostMapping("/password/resetion")
    public ResponseMessage resetTeacherPassword(@RequestParam("id") String teacherId) {
        managerService.resetTeacherPassword(teacherId);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "删除教师")
    @PostMapping("/teacher/deletion")
    public ResponseMessage deleteTeacherInfo(@RequestParam("teacherId") String teacherId) {
        managerService.deleteTeacherInfo(teacherId);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "获取所有学期信息")
    @GetMapping("/semester/all")
    public ResponseMessage<RespSemesterInfo> getAllSemesterInfo() {
        return new ResponseMessage.Success<>(managerService.getAllSemesterInfo());
    }

    @ApiOperation(value = "新增学期信息")
    @PostMapping("/semester/creation")
    public ResponseMessage addSemesterInfo(@RequestBody ReqAddSemester reqAddSemester) {
        managerService.addSemesterInfo(reqAddSemester);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "更新学期信息")
    @PostMapping("/semester/updation")
    public ResponseMessage updateSemesterInfo(@RequestBody ReqUpdateSemester reqUpdateSemester) {
        managerService.updateSemesterInfo(reqUpdateSemester);
        return new ResponseMessage.Success();
    }

    @ApiOperation(value = "删除学期")
    @PostMapping("/semester/deletion")
    public ResponseMessage deleteSemesterInfo(@RequestParam("semesterId") int semesterId) {
        managerService.deleteSemesterInfo(semesterId);
        return new ResponseMessage.Success();
    }
}
