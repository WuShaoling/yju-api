package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Experiment;
import com.guanshan.phoenix.dao.mapper.ExperimentMapper;
import com.guanshan.phoenix.dao.mapper.ModuleMapper;
import com.guanshan.phoenix.enums.CloudwareTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ExperimentService;
import com.guanshan.phoenix.webdomain.request.ReqDeleteExperiment;
import com.guanshan.phoenix.webdomain.request.ReqExperiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperimentServiceImp implements ExperimentService {

    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private ModuleMapper moduleMapper;


    @Override
    public int deleteExperiment(ReqDeleteExperiment reqDeleteExperiment) throws ApplicationErrorException {
        if(experimentMapper.selectByPrimaryKey(reqDeleteExperiment.getId()) == null){
            throw new ApplicationErrorException(ErrorCode.ExperimentNotFound);
        }

        if(experimentMapper.isExperimentUsedByStudentExperiment(reqDeleteExperiment.getId())){
            throw new ApplicationErrorException(ErrorCode.ExperimentUsedByStudentExperiment);
        }

        experimentMapper.deleteByPrimaryKey(reqDeleteExperiment.getId());

        return 0;
    }

    @Override
    public int createExperiment(ReqExperiment reqExperiment) throws ApplicationErrorException {
        CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(reqExperiment.getCloudwareType());
        if(cloudwareType == null){
            throw new ApplicationErrorException(ErrorCode.InvalidCloudwareType);
        }

        Experiment experiment = new Experiment();
        experiment.setName(reqExperiment.getExperimentName());
        experiment.setModuleId(reqExperiment.getModuleId());
        experiment.setCloudwareType(reqExperiment.getCloudwareType());
        experiment.setExperimentContent(reqExperiment.getExperimentContent());

        validateExperiment(experiment);

        experimentMapper.insert(experiment);

        return 0;
    }

    @Override
    public int updateExperiment(ReqExperiment reqExperiment) throws ApplicationErrorException {

        Experiment experiment = experimentMapper.selectByPrimaryKey(reqExperiment.getId());

        if(experiment == null){
            throw new ApplicationErrorException(ErrorCode.ExperimentNotFound);
        }

        experiment.setName(reqExperiment.getExperimentName());
        experiment.setCloudwareType(reqExperiment.getCloudwareType());
        experiment.setExperimentContent(reqExperiment.getExperimentContent());

        validateExperiment(experiment);

        experimentMapper.updateByPrimaryKeySelective(experiment);

        return 0;
    }

    private void validateExperiment(Experiment experiment) throws ApplicationErrorException {
        if(moduleMapper.selectByPrimaryKey(experiment.getModuleId()) == null){
            throw new ApplicationErrorException(ErrorCode.ModuleNotExists);
        }

        CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(experiment.getCloudwareType());
        if(cloudwareType == null){
            throw new ApplicationErrorException(ErrorCode.InvalidCloudwareType);
        }
    }
}
