package com.guanshan.phoenix.controller;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ResponseMessage;
import com.guanshan.phoenix.service.CloudwareService;
import com.guanshan.phoenix.webdomain.ReqStudentExperiment;
import com.guanshan.phoenix.webdomain.ResExperiment;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/10/19.
 */

@CrossOrigin
@RestController
@RequestMapping("cloudware")
public class CloudwareController {

    @Autowired
    private CloudwareService cloudwareService;

    @ApiOperation(value = "获取学生实验容器", notes = "根据学生id和实验id获取实验容器地址，如果没有就新产生一个")
    @GetMapping("/cloudware/experiment")
    public ResponseMessage<String> getStudentCloudware(ReqStudentExperiment reqStudentExperiment) throws ApplicationErrorException {
        return new ResponseMessage.Success<>(cloudwareService.getStudentExperiment(reqStudentExperiment));
    }


    @ApiOperation(value = "获取试验内容")
    @GetMapping("/cloudware/{id}/info")
    public ResponseMessage<ResExperiment> getExperiment(@PathVariable("id") int id) throws ApplicationErrorException{
        return new ResponseMessage.Success<>(cloudwareService.getExperiment(id));
    }
}
