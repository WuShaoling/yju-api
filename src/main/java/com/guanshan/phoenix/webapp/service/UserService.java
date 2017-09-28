package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.dao.entity.User;
import com.guanshan.phoenix.webapp.webdomain.WebTeacher;

import java.util.List;

public interface UserService {

    User getUserInfo(String username);

    List<WebTeacher> getAllTeacherInfo();
}
