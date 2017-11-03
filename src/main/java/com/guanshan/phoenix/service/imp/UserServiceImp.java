package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public User getUserInfo(int userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void deleteUserById(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
