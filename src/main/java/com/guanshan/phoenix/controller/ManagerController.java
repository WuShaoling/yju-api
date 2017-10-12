package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.service.ManagerService;
import com.guanshan.phoenix.shared.util.codec.Const;
import com.guanshan.phoenix.shared.util.codec.ResponseMessage;
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
        return new ResponseMessage(Const.SUCCESS,
                "success",
                managerService.getAllTeacherInfo());
    }

    @ApiOperation(value = "添加教师信息")
    @PostMapping("/teacher/creation")
    public ResponseMessage addTeacherInfo(@RequestBody ReqAddTeacher reqAddTeacher) {
        if (managerService.addTeacherInfo(reqAddTeacher) == 0) {
            return new ResponseMessage(Const.SUCCESS, "success");
        } else {
            return new ResponseMessage(Const.FAIL, "fail");
        }
    }

    @ApiOperation(value = "更新教师信息")
    @PostMapping("/teacher/updation")
    public ResponseMessage updateTeacherInfo(@RequestBody ReqUpdateTeacher reqUpdateTeacher) {
        if (managerService.updateTeacherInfo(reqUpdateTeacher) == 0) {
            return new ResponseMessage(Const.SUCCESS, "success");
        } else {
            return new ResponseMessage(Const.FAIL, "fail");
        }
    }

    @ApiOperation(value = "重置教师密码")
    @PostMapping("/password/resetion")
    public ResponseMessage resetTeacherPassword(@RequestParam("id") String teacherId) {
        if (managerService.resetTeacherPassword(teacherId) == 0) {
            return new ResponseMessage(Const.SUCCESS, "success");
        } else {
            return new ResponseMessage(Const.FAIL, "fail");
        }
    }

    @ApiOperation(value = "删除教师")
    @PostMapping("/teacher/deletion")
    public ResponseMessage deleteTeacherInfo(@RequestParam("teacherId") String teacherId) {
        if (managerService.deleteTeacherInfo(teacherId) == 0) {
            return new ResponseMessage(Const.SUCCESS, "success");
        } else {
            return new ResponseMessage(Const.FAIL, "fail");
        }
    }

    @ApiOperation(value = "获取所有学期信息")
    @GetMapping("/semester/all")
    public ResponseMessage<RespSemesterInfo> getAllSemesterInfo() {
        return new ResponseMessage(Const.SUCCESS,
                "success",
                managerService.getAllSemesterInfo());
    }

    @ApiOperation(value = "新增学期信息")
    @PostMapping("/semester/creation")
    public ResponseMessage addSemesterInfo(@RequestBody ReqAddSemester reqAddSemester) {
        if (managerService.addSemesterInfo(reqAddSemester) == 0) {
            return new ResponseMessage(Const.SUCCESS, "success");
        } else {
            return new ResponseMessage(Const.FAIL, "fail");
        }
    }

    @ApiOperation(value = "更新学期信息")
    @PostMapping("/semester/updation")
    public ResponseMessage updateSemesterInfo(@RequestBody ReqUpdateSemester reqUpdateSemester) {
        if (managerService.updateSemesterInfo(reqUpdateSemester) == 0) {
            return new ResponseMessage(Const.SUCCESS, "success");
        } else {
            return new ResponseMessage(Const.FAIL, "fail");
        }
    }

    @ApiOperation(value = "删除学期")
    @PostMapping("/semester/deletion")
    public ResponseMessage deleteSemesterInfo(@RequestParam("semesterId") int semesterId) {
        if (managerService.deleteSemesterInfo(semesterId) == 0) {
            return new ResponseMessage(Const.SUCCESS, "success");
        } else {
            return new ResponseMessage(Const.FAIL, "fail");
        }
    }
}
