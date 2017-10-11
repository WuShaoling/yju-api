package com.guanshan.phoenix.authentication.authUser;

import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/2.
 * Write implements here for project.
 */
@Service
public class AuthUserInfoServiceImp implements AuthUserInfoService {

    @Autowired
    private UserService userService;

    @Override
    public AuthUserInfo getUserInfo(String username) {
//        User user = studentService.getStudentInfo(username);
        User user = userService.getUserInfo(username);
        if (user == null) {
            return null;
        }
        AuthUserInfo authUserInfo = new AuthUserInfo();
        authUserInfo.setUsername(user.getUsername());
        authUserInfo.setPassword(user.getPassword());
        return authUserInfo;
    }

    @Override
    public AuthUserInfo addUserInfo(AuthUserInfo authUserInfoToAdd) {
        return null;
    }
}
