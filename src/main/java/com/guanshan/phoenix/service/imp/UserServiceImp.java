package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao2.entity.Teacher;
import com.guanshan.phoenix.dao2.entity.User;
import com.guanshan.phoenix.dao2.mapper.UserMapper;
import com.guanshan.phoenix.dao2.mapper.UserNavbarMapper;
import com.guanshan.phoenix.enums.GenderEnum;
import com.guanshan.phoenix.enums.TitleEnum;
import com.guanshan.phoenix.service.UserService;
import com.guanshan.phoenix.dao2.entity.UserNavbar;
import com.guanshan.phoenix.dao2.mapper.NavbarMapper;
import com.guanshan.phoenix.dao2.mapper.TeacherMapper;
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

    @Autowired
    private UserNavbarMapper userNavbarMapper;

    @Autowired
    private NavbarMapper navbarMapper;

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

    @Override
    public WebUserNavbar getUserNavbar(int userId) {
        WebUserNavbar webUserNavbar = new WebUserNavbar();

        webUserNavbar.setUserId(userId);
        List<String> navbarList = new ArrayList<>();
        List<UserNavbar> userNavbars = userNavbarMapper.findAllByUserId(userId);
        for (UserNavbar userNavbar : userNavbars) {
            navbarList.add(navbarMapper.findOne(userNavbar.getNavbar_id()).getNav_url());
        }
        webUserNavbar.setNavbarList(navbarList);

        return webUserNavbar;
    }
}
