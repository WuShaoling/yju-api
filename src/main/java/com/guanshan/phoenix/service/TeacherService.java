package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqDeleteTeacher;
import com.guanshan.phoenix.webdomain.request.ReqHomeworkGrade;
import com.guanshan.phoenix.webdomain.request.ReqUpdateTeacher;
import com.guanshan.phoenix.webdomain.response.ResBatchAddTeacher;
import com.guanshan.phoenix.webdomain.response.ResTeacherClassList;
import com.guanshan.phoenix.webdomain.response.ResTeacherList;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {
    Teacher getTeacherByUserId(int teacherID) throws ApplicationErrorException;

    ResTeacherClassList getAllTeacherClassInfoByUserId(int teacherId) throws ApplicationErrorException;

    void gradeHomework(ReqHomeworkGrade homeworkGrade) throws ApplicationErrorException;

    ResTeacherList getAllTeacherList() throws ApplicationErrorException;

    void updateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException;

    void createTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException;

    void deleteTeacherByTeacherUserId(ReqDeleteTeacher reqDeleteTeacher) throws ApplicationErrorException;

    ResBatchAddTeacher batchTeacherCreation(MultipartFile file) throws ApplicationErrorException;
}
