package com.guanshan.phoenix.webapp.controller;

import com.guanshan.phoenix.webapp.dao.entity.Task;
import com.guanshan.phoenix.webapp.service.TaskService;
import com.guanshan.phoenix.webapp.shared.util.codec.Const;
import com.guanshan.phoenix.webapp.shared.util.codec.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/9/22.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public ResponseMessage<Integer> updateExperimentTask(@RequestBody Task task) {
        try {
            return new ResponseMessage<>(Const.SUCCESS,
                    "success",
                    taskService.updateExperimentTask(task)) ;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage<>(Const.FAIL, "fail");
        }
    }
}
