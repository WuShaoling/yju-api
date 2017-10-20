package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.StudentClass;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ReqUpdateStudent;
import com.guanshan.phoenix.webdomain.ResStudentClassList;

import java.util.List;

public interface StudentService {
    List<StudentClass> getAllStudentClassByUserId(int studentID) throws ApplicationErrorException;

    ResStudentClassList getAllStudentClassInfoByUserId(int studentID) throws ApplicationErrorException;

    int updateStudentInfo(ReqUpdateStudent reqUpdateStudent) throws ApplicationErrorException;
}
