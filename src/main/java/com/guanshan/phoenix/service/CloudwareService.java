package com.guanshan.phoenix.service;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ReqStudentExperiment;
import com.guanshan.phoenix.webdomain.ResExperiment;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface CloudwareService {

    String getStudentExperiment(ReqStudentExperiment reqStudentExperiment) throws ApplicationErrorException;

    ResExperiment getExperiment(int id) throws ApplicationErrorException;
}
