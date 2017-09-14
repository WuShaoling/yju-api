package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.service.UserService;
import com.guanshan.phoenix.webapp.shared.entity.User;
import com.guanshan.phoenix.webapp.shared.util.codec.*;
import com.guanshan.phoenix.webapp.shared.util.codec.MD5;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dell on 2017/7/21.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static Logger LOG = Logger.getLogger(UserController.class);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @RequestMapping(value="/test", method = RequestMethod.POST)
    public ResponseMessage<Integer> testMethod(
            @RequestBody Map request) {
        ResponseMessage<Integer> result = new ResponseMessage();
        result.setData(1);
        return result;
    }

    @PostMapping(value = "/save")
    public ResponseMessage<Integer> saveUser(@RequestBody User user){
        ResponseMessage<Integer> result  = new ResponseMessage();
        user.setPassword(MD5.MD5Encode(user.getPassword()));
//        result.setData( userService.saveUser(user));
        result.setData(1);
        return result;
    }




}
