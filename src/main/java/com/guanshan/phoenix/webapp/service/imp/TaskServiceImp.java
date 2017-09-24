package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Task;
import com.guanshan.phoenix.webapp.dao.mapper.TaskMapper;
import com.guanshan.phoenix.webapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/22.
 */
@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public int updateExperimentTask(Task task) {
        try {
            taskMapper.update(task);
        } catch (Exception e) {
            taskMapper.insert(task);
        }
        return 0;
    }
}
