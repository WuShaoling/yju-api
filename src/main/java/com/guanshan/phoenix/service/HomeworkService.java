package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.*;

public interface HomeworkService {
    ResHomeworkDetail getHomeworkDetail(int homeworkID) throws ApplicationErrorException;

    ResHomeworkSubmissionList getAllHomeworkSubmissionByModuleId(int moduleId) throws ApplicationErrorException;

    ResStudentHomeworkDetail getStudentHomeworkDetailById(int studentHomeworkId) throws ApplicationErrorException;

    int deleteHomework(int homeworkID) throws ApplicationErrorException;

    int updateHomework(ReqUpdateHomework reqUpdateHomework) throws ApplicationErrorException;

    int createHomework(ReqCreateHomework reqCreateHomework) throws ApplicationErrorException;

    ResClassHomework getClassHomework(int classId) throws ApplicationErrorException;
}
