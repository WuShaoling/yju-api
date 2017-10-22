package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ReqHomeworkGrade;
import com.guanshan.phoenix.webdomain.ReqUpdateTeacher;
import com.guanshan.phoenix.webdomain.ResTeacherClassList;
import com.guanshan.phoenix.webdomain.ResTeacherList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherService {
    Teacher getTeacherByUserId(int teacherID) throws ApplicationErrorException;

    ResTeacherClassList getAllTeacherClassInfoByUserId(int teacherId) throws ApplicationErrorException;

    void gradeHomework(ReqHomeworkGrade homeworkGrade) throws ApplicationErrorException;

    ResTeacherList getAllTeacherList() throws ApplicationErrorException;

    void updateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException;

    void createTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException;

    void deleteTeacherByTeacherUserId(int teacherId) throws ApplicationErrorException;

    int batchTeacherCreation(MultipartFile file) throws ApplicationErrorException;
}
