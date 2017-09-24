package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Teacher;
import com.guanshan.phoenix.webapp.dao.mapper.TeacherMapper;
import com.guanshan.phoenix.webapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/22.
 */

@Service
public class TeacherServiceImp implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherInfo(String username) {
        return teacherMapper.findOneByUsername(username);
    }
}
