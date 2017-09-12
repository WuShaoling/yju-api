package com.guanshan.nlp.webapp.controller;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.user.UserInfoResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guanshan.nlp.webapp.service.UserService;
import com.guanshan.nlp.webapp.shared.entity.User;
import com.guanshan.nlp.webapp.shared.util.codec.*;
import com.guanshan.nlp.webapp.shared.util.JPush.JMessageHelper;
import com.guanshan.nlp.webapp.shared.util.JPush.JSMSHelper;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
