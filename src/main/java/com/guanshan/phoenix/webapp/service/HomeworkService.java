package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.webdomain.WebHomework;
import com.guanshan.phoenix.webapp.webdomain.WebHomeworkDetail;

import java.util.List;

public interface HomeworkService {

    List<WebHomework> getPeriodHomeworkInfo(int periodId);

    WebHomeworkDetail getHomeworkInfoDetail(int homeworkId);
}
