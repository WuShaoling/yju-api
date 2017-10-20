package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImp implements ManagerService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int resetPassword(int userId) throws ApplicationErrorException {
        User user = new User();
        user.setId(userId);
        // todo reset strategy
        user.setPassword("I am a password");
        userMapper.updateByPrimaryKeySelective(user);

        return 0;
    }
}
