package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.StudentClass;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResStudentClassList;

import java.util.List;

public interface StudentService {
    List<StudentClass> getAllStudentClassById(int studentID);

    ResStudentClassList getAllStudentClassInfoById(int studentID) throws ApplicationErrorException;
}
