package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqCreateHomework;
import com.guanshan.phoenix.webdomain.request.ReqDeleteHomework;
import com.guanshan.phoenix.webdomain.request.ReqUpdateHomework;
import com.guanshan.phoenix.webdomain.response.ResClassHomework;
import com.guanshan.phoenix.webdomain.response.ResHomeworkDetail;
import com.guanshan.phoenix.webdomain.response.ResHomeworkSubmissionList;
import com.guanshan.phoenix.webdomain.response.ResStudentHomeworkDetail;

public interface HomeworkService {
    ResHomeworkDetail getHomeworkDetail(int homeworkID) throws ApplicationErrorException;

    ResHomeworkSubmissionList getAllHomeworkSubmissionByModuleId(int moduleId) throws ApplicationErrorException;

    ResStudentHomeworkDetail getStudentHomeworkDetailById(int studentHomeworkId) throws ApplicationErrorException;

    int deleteHomework(ReqDeleteHomework reqDeleteHomework) throws ApplicationErrorException;

    int updateHomework(ReqUpdateHomework reqUpdateHomework) throws ApplicationErrorException;

    int createHomework(ReqCreateHomework reqCreateHomework) throws ApplicationErrorException;

    ResClassHomework getClassHomework(int classId) throws ApplicationErrorException;
}
