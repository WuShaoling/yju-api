package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.service.UserService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import com.guanshan.phoenix.webapp.webdomain.WebTeacher;
import com.guanshan.phoenix.webapp.webdomain.WebUserNavbar;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取全部的教师信息")
    @GetMapping("teacher/all")
    public ResponseMessage<List<WebTeacher>> getAllTeacherInfo() {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                userService.getAllTeacherInfo());
    }

    @ApiOperation(value = "获取用户侧边栏列表")
    @GetMapping("navbar")
    public ResponseMessage<WebUserNavbar> getUserNavbar(@RequestParam("userId") int userId) {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                userService.getUserNavbar(userId));
    }
}
