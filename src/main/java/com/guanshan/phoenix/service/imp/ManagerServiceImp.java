package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.enums.RoleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerServiceImp implements ManagerService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int resetPassword(int userId) throws ApplicationErrorException {
        User user = new User();
        user.setId(userId);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = "12345678";
        user.setPassword(encoder.encode(rawPassword));
        userMapper.updateByPrimaryKeySelective(user);

        return 0;
    }

    @Override
    public User createUser(String username, RoleEnum role) {
        User user = new User();

        user.setUsername(username);
        user.setRole(role.getCode());
        user.setPassword("I am a password");

        userMapper.insert(user);
        return user;
    }
}
