package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Homework;
import com.guanshan.phoenix.webapp.dao.mapper.HomeworkMapper;
import com.guanshan.phoenix.webapp.service.HomeworkService;
import com.guanshan.phoenix.webapp.webdomain.WebHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkServiceImp implements HomeworkService {

    @Autowired
    private HomeworkMapper homeworkMapper;


    @Override
    public List<WebHomework> getPeriodHomeworkInfo(int periodId) {
        List<WebHomework> webHomeworks = new ArrayList<>();

        List<Homework> homeworks = homeworkMapper.findByPeriodId(periodId);

        for (Homework homework : homeworks) {
            webHomeworks.add(new WebHomework(homework.getId(),
                    homework.getName(),
                    homework.getDescription()));
        }

        return webHomeworks;
    }
}
