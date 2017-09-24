package com.guanshan.phoenix.webapp.authentication.authUser;

import com.guanshan.phoenix.webapp.dao.entity.User;
import com.guanshan.phoenix.webapp.service.InstructorService;
import com.guanshan.phoenix.webapp.service.StudentService;
import com.guanshan.phoenix.webapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/2.
 * Write implements here for project.
 */
@Service
public class AuthUserInfoServiceImp implements AuthUserInfoService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private InstructorService instructorService;

    @Override
    public AuthUserInfo getUserInfo(String username) {
//        User user = studentService.getStudentInfo(username);
        User user = instructorService.getInstructorInfo(username);
        if (user == null) {
            user = studentService.getStudentInfo(username);
        }
        if (user == null) {
            user = instructorService.getInstructorInfo(username);

        }
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
