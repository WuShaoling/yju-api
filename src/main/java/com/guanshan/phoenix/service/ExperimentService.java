package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ReqExperiment;
import org.springframework.web.multipart.MultipartFile;


public interface ExperimentService {

    int deleteExperiment(int id) throws ApplicationErrorException;

    int createExperiment(ReqExperiment reqExperiment) throws ApplicationErrorException;

    int updateExperiment(ReqExperiment reqExperiment) throws ApplicationErrorException;
}
