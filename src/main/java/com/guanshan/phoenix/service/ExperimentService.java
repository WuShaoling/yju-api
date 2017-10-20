package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ReqExperiment;

public interface ExperimentService {

    int deleteExperiment(int id) throws ApplicationErrorException;

    int createExperiment(ReqExperiment reqExperiment);

    int updateExperiment(ReqExperiment reqExperiment);
}
