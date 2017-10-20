package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ReqDeleteClassStudent;
import com.guanshan.phoenix.webdomain.ResClassDetail;
import com.guanshan.phoenix.webdomain.ResClassStudents;

public interface ClassService {
    Clazz getClassById(int classID) throws ApplicationErrorException;

    ResClassDetail getClassDetailInfo(int classID) throws ApplicationErrorException;

    int deleteClassStudent(ReqDeleteClassStudent reqDeleteClassStudent) throws ApplicationErrorException;

    int addClassStudent(int classId, int studentId);

    ResClassStudents getAllClassStudentInfo(int classId);
}
