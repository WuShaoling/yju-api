package com.guanshan.phoenix.service;

import com.guanshan.phoenix.webdomain.*;

public interface ManagerService {

    RespTeacherInfo getAllTeacherInfo();

    int addTeacherInfo(ReqAddTeacher reqAddTeacher);

    int updateTeacherInfo(ReqUpdateTeacher reqUpdateTeacher);

    int resetTeacherPassword(String teacherId);

    int deleteTeacherInfo(String teacherId);

    RespSemesterInfo getAllSemesterInfo();

    int addSemesterInfo(ReqAddSemester reqAddSemester);

    int updateSemesterInfo(ReqUpdateSemester reqUpdateSemester);

    int deleteSemesterInfo(int semesterId);

}
