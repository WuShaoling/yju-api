package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ReqHomeworkGrade;
import com.guanshan.phoenix.webdomain.ReqUpdateTeacher;
import com.guanshan.phoenix.webdomain.ResTeacherClassList;
import com.guanshan.phoenix.webdomain.ResTeacherList;

import java.util.List;

public interface TeacherService {
    Teacher getTeacherById(int teacherID) throws ApplicationErrorException;

    ResTeacherClassList getAllTeacherClassInfoById(int teacherId) throws ApplicationErrorException;

    void gradeHomework(ReqHomeworkGrade homeworkGrade) throws ApplicationErrorException;

    ResTeacherList getAllTeacherList() throws ApplicationErrorException;

    void updateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException;

    void deleteTeacherByTeacherId(int teacherId);
}
