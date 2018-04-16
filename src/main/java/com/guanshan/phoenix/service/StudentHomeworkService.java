package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Cloudware;
import com.guanshan.phoenix.dao.entity.StudentHomework;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqHomeworkSubmission;
import com.guanshan.phoenix.webdomain.request.ReqStudentHomeworkCloudware;

public interface StudentHomeworkService {
    void submitStudentHomework(ReqHomeworkSubmission homeworkSubmission) throws ApplicationErrorException;

    void validStudentHomeWork(int studentId, int homeworkId) throws ApplicationErrorException;

    StudentHomework getStudentHomeworkById(int studentHomeworkId) throws ApplicationErrorException;

    Cloudware getStudentHomeworkCloudware(int homeworkId, int studentId) throws ApplicationErrorException;

    Cloudware createStudentHomeworkCloudware(ReqStudentHomeworkCloudware reqStudentHomeworkCloudware) throws ApplicationErrorException, InterruptedException;

    void deleteStudentHomework(int studentHomeworkId) throws ApplicationErrorException;
}
