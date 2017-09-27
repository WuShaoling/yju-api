package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.User;
import com.guanshan.phoenix.webapp.dao.mapper.UserMapper;
import com.guanshan.phoenix.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String username) {
        return userMapper.findOneByUsername(username);
    }
}
