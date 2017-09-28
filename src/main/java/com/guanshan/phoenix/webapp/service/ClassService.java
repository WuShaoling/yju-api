package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.webdomain.WebClass;
import com.guanshan.phoenix.webapp.webdomain.WebClassDetail;

import java.util.List;

public interface ClassService {

    List<WebClassDetail> getClassDetail(Integer classId);

    List<WebClass> getAllClassesInfo();
}
