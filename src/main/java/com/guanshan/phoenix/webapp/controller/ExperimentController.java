package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.dao.entity.Experiment;
import com.guanshan.phoenix.webapp.service.ExperimentService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
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
@RequestMapping(value = "experiment")
public class ExperimentController {
    @Autowired
    private ExperimentService experimentService;

    @ApiOperation(value = "获取课时试验", notes = "根据课时ID返回课时的试验列表")
    @GetMapping(value = "all")
    public ResponseMessage<List<Experiment>> getAllExperiments(@RequestParam("periodId") Integer periodId) {
        try {
            return new ResponseMessage<>(Const.SUCCESS,
                    "success",
                    experimentService.getAllExperiments(periodId)) ;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage<>(Const.FAIL, "fail");
        }
    }

}
