package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.User;

public interface UserService {

    User getUserInfo(String username);

    User getUserInfo(int userId);

    void deleteUserById(int id);

    void updateUser(User user);
}
