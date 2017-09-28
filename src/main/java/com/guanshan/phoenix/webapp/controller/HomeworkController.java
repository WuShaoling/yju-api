package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.service.HomeworkService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import com.guanshan.phoenix.webapp.webdomain.WebHomework;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("homework")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @ApiOperation(value = "获取课时作业模版信息")
    @GetMapping("template")
    public ResponseMessage<List<WebHomework>> getPeriodHomeworkInfo(@RequestParam("periodId") int periodId) {
        return new ResponseMessage<>(Const.SUCCESS,
                "success",
                homeworkService.getPeriodHomeworkInfo(periodId));
    }

//    @ApiOperation(value = "获取已提交作业的详情")
//    @GetMapping("")

}
