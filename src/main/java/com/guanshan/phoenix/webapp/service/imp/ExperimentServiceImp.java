package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Experiment;
import com.guanshan.phoenix.webapp.dao.mapper.ExperimentMapper;
import com.guanshan.phoenix.webapp.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
@Service
public class ExperimentServiceImp implements ExperimentService {
    @Autowired
    private ExperimentMapper experimentMapper;

    @Override
    public List<Experiment> getAllExperiments(Integer periodId) {
        return experimentMapper.findAllByPeriodId(periodId);
    }
}
