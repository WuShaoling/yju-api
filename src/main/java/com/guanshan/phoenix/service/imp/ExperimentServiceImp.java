package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Experiment;
import com.guanshan.phoenix.dao.mapper.ExperimentMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.service.ExperimentService;
import com.guanshan.phoenix.webdomain.ReqExperiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperimentServiceImp implements ExperimentService {

    @Autowired
    private ExperimentMapper experimentMapper;


    @Override
    public int deleteExperiment(int id) throws ApplicationErrorException {
        experimentMapper.deleteByPrimaryKey(id);

        return 0;
    }

    @Override
    public int createExperiment(ReqExperiment reqExperiment) {
        Experiment experiment = new Experiment();
        experiment.setModuleId(reqExperiment.getModuleId());
        experiment.setName(reqExperiment.getExperimentName());
        experiment.setCloudwareType(reqExperiment.getCloudwareType());
        experiment.setDescription(reqExperiment.getExperimentContent());
        experimentMapper.insertSelective(experiment);

        return 0;
    }

    @Override
    public int updateExperiment(ReqExperiment reqExperiment) {
        Experiment experiment = new Experiment();
        experiment.setId(reqExperiment.getId());
        experiment.setModuleId(reqExperiment.getModuleId());
        experiment.setName(reqExperiment.getExperimentName());
        experiment.setCloudwareType(reqExperiment.getCloudwareType());
        experiment.setDescription(reqExperiment.getExperimentContent());
        experimentMapper.updateByPrimaryKeySelective(experiment);

        return 0;
    }
}
