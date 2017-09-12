package com.guanshan.nlp.webapp.dao;

import com.guanshan.nlp.webapp.shared.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDao {

    void insertUser(User user);
}
