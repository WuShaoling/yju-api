package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Cloudware;
import com.guanshan.phoenix.dao.entity.StudentHomework;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqHomeworkSubmission;
import com.guanshan.phoenix.webdomain.request.ReqStudentExperimentCloudware;
import com.guanshan.phoenix.webdomain.request.ReqStudentHomeworkCloudware;

public interface StudentExperimentService {
    Cloudware getStudentExperimentCloudware(int experimentId, int studentId) throws ApplicationErrorException;

    void createStudentExperimentCloudware(ReqStudentExperimentCloudware reqStudentExperimentCloudware) throws ApplicationErrorException;

    void deleteStudentExperiment(int studentExperimentId) throws ApplicationErrorException;
}
