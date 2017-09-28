package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Teacher;
import com.guanshan.phoenix.webapp.dao.entity.User;
import com.guanshan.phoenix.webapp.dao.mapper.TeacherMapper;
import com.guanshan.phoenix.webapp.dao.mapper.UserMapper;
import com.guanshan.phoenix.webapp.enums.GenderEnum;
import com.guanshan.phoenix.webapp.enums.TitleEnum;
import com.guanshan.phoenix.webapp.service.UserService;
import com.guanshan.phoenix.webapp.webdomain.WebTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public User getUserInfo(String username) {
        return userMapper.findOneByUsername(username);
    }

    @Override
    public List<WebTeacher> getAllTeacherInfo() {
        List<WebTeacher> webTeachers = new ArrayList<>();

        List<Teacher> teachers = teacherMapper.findAll();
        for (Teacher teacher : teachers) {
            webTeachers.add(new WebTeacher(teacher.getId(),
                    teacher.getName(),
                    TitleEnum.getStringByCode(teacher.getTitle()),
                    GenderEnum.getStringByCode(teacher.getGender()),
                    teacher.getContact()));
        }
        return webTeachers;
    }
}
