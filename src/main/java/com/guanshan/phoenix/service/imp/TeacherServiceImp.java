package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.dao.mapper.TeacherMapper;
import com.guanshan.phoenix.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImp implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherById(int teacherID) {
        return teacherMapper.selectByPrimaryKey(teacherID);
    }
}
