package com.guanshan.phoenix.service;

import com.guanshan.phoenix.webdomain.*;

public interface ManagerService {

    RespTeacherInfo getAllTeacherInfo();

    void addTeacherInfo(ReqAddTeacher reqAddTeacher);

    void updateTeacherInfo(ReqUpdateTeacher reqUpdateTeacher);

    void resetTeacherPassword(String teacherId);

    void deleteTeacherInfo(String teacherId);

    RespSemesterInfo getAllSemesterInfo();

    void addSemesterInfo(ReqAddSemester reqAddSemester);

    void updateSemesterInfo(ReqUpdateSemester reqUpdateSemester);

    void deleteSemesterInfo(int semesterId);

}
