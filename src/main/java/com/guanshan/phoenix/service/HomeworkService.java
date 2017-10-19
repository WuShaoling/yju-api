package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResHomeworkDetail;
import com.guanshan.phoenix.webdomain.ResHomeworkSubmissionList;

public interface HomeworkService {
    ResHomeworkDetail getHomeworkDetail(int homeworkID) throws ApplicationErrorException;

    ResHomeworkSubmissionList getAllHomeworkSubmissionByModuleId(int moduleId);
}
