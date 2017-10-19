package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResHomeworkDetail;
import com.guanshan.phoenix.webdomain.ResHomeworkSubmissionList;
import com.guanshan.phoenix.webdomain.ResStudentHomeworkDetail;

public interface HomeworkService {
    ResHomeworkDetail getHomeworkDetail(int homeworkID) throws ApplicationErrorException;

    ResHomeworkSubmissionList getAllHomeworkSubmissionByModuleId(int moduleId);

    ResStudentHomeworkDetail getStudentHomeworkDetailById(int studentHomeworkId) throws ApplicationErrorException;
}
