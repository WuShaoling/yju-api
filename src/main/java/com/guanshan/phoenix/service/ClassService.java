package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.*;

public interface ClassService {
    Clazz getClassById(int classID) throws ApplicationErrorException;

    ResClassDetail getClassDetailInfo(int classID) throws ApplicationErrorException;

    int deleteClassStudent(ReqDeleteClassStudent reqDeleteClassStudent) throws ApplicationErrorException;

    int addClassStudent(ReqAddClassStudent reqAddClassStudent);

    ResClassStudents getAllClassStudentInfo(int classId);

    int deleteClass(int classId) throws ApplicationErrorException;

    int updateClassInfo(ReqUpdateClass reqUpdateClass) throws ApplicationErrorException;

    int createClass(ReqAddClass reqAddClass) throws ApplicationErrorException;

    ResClassInfos getAllClassInfo();
}
