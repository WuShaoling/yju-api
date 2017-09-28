package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.webdomain.WebHomework;

import java.util.List;

public interface HomeworkService {

    List<WebHomework> getPeriodHomeworkInfo(int periodId);
}
