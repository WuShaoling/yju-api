package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Homework;
import com.guanshan.phoenix.webapp.dao.entity.StudentHomework;
import com.guanshan.phoenix.webapp.dao.mapper.HomeworkMapper;
import com.guanshan.phoenix.webapp.dao.mapper.StudentHomeworkMapper;
import com.guanshan.phoenix.webapp.dao.mapper.StudentMapper;
import com.guanshan.phoenix.webapp.service.HomeworkService;
import com.guanshan.phoenix.webapp.webdomain.WebHomework;
import com.guanshan.phoenix.webapp.webdomain.WebHomeworkDetail;
import com.guanshan.phoenix.webapp.webdomain.WebStudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkServiceImp implements HomeworkService {

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private StudentHomeworkMapper studentHomeworkMapper;

    @Autowired
    private StudentMapper studentMapper;


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

    @Override
    public WebHomeworkDetail getHomeworkInfoDetail(int homeworkId) {
        WebHomeworkDetail webHomeworkDetail = new WebHomeworkDetail();

        Homework homework = homeworkMapper.findOne(homeworkId);
        webHomeworkDetail.setHomeworkId(homework.getId());
        webHomeworkDetail.setHomeworkName(homework.getName());

        List<WebStudentHomework> webStudentHomeworks = new ArrayList<>();
        List<StudentHomework> studentHomeworks = studentHomeworkMapper.findByPeriodId(homework.getPeriod_id());
        for (StudentHomework studentHomework : studentHomeworks) {
            webStudentHomeworks.add(new WebStudentHomework(studentHomework.getStudent_id(),
                    studentMapper.findOne(studentHomework.getStudent_id()).getName(),
                    studentHomework.getCloudware_id(),
                    studentHomework.getAnswer_url(),
                    studentHomework.getAnswer_content(),
                    studentHomework.getScore_comments(),
                    studentHomework.getScore()));
        }
        webHomeworkDetail.setWebStudentHomeworks(webStudentHomeworks);

        return webHomeworkDetail;
    }
}
