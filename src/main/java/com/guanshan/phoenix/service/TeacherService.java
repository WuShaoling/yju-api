package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResTeacherClassList;

public interface TeacherService {
    Teacher getTeacherById(int teacherID) throws ApplicationErrorException;

    ResTeacherClassList getAllTeacherClassInfoById(int teacherId) throws ApplicationErrorException;
}
