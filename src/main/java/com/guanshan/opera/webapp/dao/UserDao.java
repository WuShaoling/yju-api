package com.guanshan.opera.webapp.dao;

import com.guanshan.opera.webapp.shared.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDao {

    void insertUser(User user);
}
