package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao2.entity.User;

import java.util.List;

public interface UserService {

    User getUserInfo(String username);

    List<WebTeacher> getAllTeacherInfo();

    WebUserNavbar getUserNavbar(int userId);
}
