package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.dao.entity.Period;
import com.guanshan.phoenix.webapp.service.PeriodService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "period")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @ApiOperation(value = "获取课时列表", notes = "根据课程ID返回该课的课时列表")
    @GetMapping(value = "all")
    public ResponseMessage<List<Period>> getAllPeriod(@RequestParam("courseId") Integer courseId){
        try {
            return new ResponseMessage<>(Const.SUCCESS,
                    "success",
                    periodService.getAllPeriods(courseId));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage<>(Const.FAIL, "fail");
        }

    }
}
