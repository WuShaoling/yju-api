package com.guanshan.phoenix.webapp.dao;

import com.guanshan.phoenix.webapp.shared.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDao {

    void insertUser(User user);
}
