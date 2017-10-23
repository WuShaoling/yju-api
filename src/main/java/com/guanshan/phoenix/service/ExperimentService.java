package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqDeleteExperiment;
import com.guanshan.phoenix.webdomain.request.ReqExperiment;


public interface ExperimentService {

    int deleteExperiment(ReqDeleteExperiment reqDeleteExperiment) throws ApplicationErrorException;

    int createExperiment(ReqExperiment reqExperiment) throws ApplicationErrorException;

    int updateExperiment(ReqExperiment reqExperiment) throws ApplicationErrorException;
}
