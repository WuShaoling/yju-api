package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Experiment;
import com.guanshan.phoenix.dao.entity.Module;
import com.guanshan.phoenix.dao.mapper.CourseMapper;
import com.guanshan.phoenix.dao.mapper.ExperimentMapper;
import com.guanshan.phoenix.dao.mapper.ModuleMapper;
import com.guanshan.phoenix.dao.mapper.StudentExperimentMapper;
import com.guanshan.phoenix.enums.ImageTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ExperimentService;
import com.guanshan.phoenix.service.StudentExperimentService;
import com.guanshan.phoenix.webdomain.request.ReqDeleteExperiment;
import com.guanshan.phoenix.webdomain.request.ReqExperiment;
import com.guanshan.phoenix.webdomain.response.ResExperimentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperimentServiceImp implements ExperimentService {

    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentExperimentMapper studentExperimentMapper;

    @Autowired
    private StudentExperimentService studentExperimentService;

    @Override
    public int deleteExperiment(ReqDeleteExperiment reqDeleteExperiment) throws ApplicationErrorException {
        if(experimentMapper.selectByPrimaryKey(reqDeleteExperiment.getId()) == null){
            throw new ApplicationErrorException(ErrorCode.ExperimentNotFound);
        }

//        if(experimentMapper.isExperimentUsedByStudentExperiment(reqDeleteExperiment.getId())){
//            throw new ApplicationErrorException(ErrorCode.ExperimentUsedByStudentExperiment);
//        }

        for(int studentExperimentId :
                studentExperimentMapper.selectStudentExperimentByExperimentId(reqDeleteExperiment.getId())){
            studentExperimentService.deleteStudentExperiment(studentExperimentId);
        }

        experimentMapper.deleteByPrimaryKey(reqDeleteExperiment.getId());

        return 0;
    }

    @Override
    public int createExperiment(ReqExperiment reqExperiment) throws ApplicationErrorException {
        int imageId = validateExperiment(reqExperiment);

        Experiment experiment = new Experiment();
        experiment.setName(reqExperiment.getExperimentName());
        experiment.setModuleId(reqExperiment.getModuleId());
        experiment.setImageId(imageId);
        experiment.setPublishDate(reqExperiment.getExperimentCreateDate());
        experiment.setDeadlineDate(reqExperiment.getExperimentDueDate());
        experiment.setExperimentContent(reqExperiment.getExperimentContent());
        experiment.setDescription(reqExperiment.getExperimentDes());

        experimentMapper.insert(experiment);

        return 0;
    }

    @Override
    public int updateExperiment(ReqExperiment reqExperiment) throws ApplicationErrorException {
        int imageId = validateExperiment(reqExperiment);

        Experiment experiment = experimentMapper.selectByPrimaryKey(reqExperiment.getId());

        if(experiment == null){
            throw new ApplicationErrorException(ErrorCode.ExperimentNotFound);
        }

        experiment.setName(reqExperiment.getExperimentName());
        experiment.setImageId(imageId);
        experiment.setExperimentContent(reqExperiment.getExperimentContent());
        experiment.setDescription(reqExperiment.getExperimentDes());

        experimentMapper.updateByPrimaryKeySelective(experiment);

        return 0;
    }

    @Override
    public ResExperimentInfo getExperiment(int experimentId) throws ApplicationErrorException {
        Experiment experiment = experimentMapper.selectByPrimaryKey(experimentId);
        if(experiment == null){
            throw new ApplicationErrorException(ErrorCode.ExperimentNotFound);
        }

        Module module = moduleMapper.selectByPrimaryKey(experiment.getModuleId());
        Course course = courseMapper.selectByPrimaryKey(module.getCourseId());
        ResExperimentInfo experimentInfo = new ResExperimentInfo(experiment);
        experimentInfo.setModuleName(module.getName());
        experimentInfo.setCourseName(course.getName());

        return experimentInfo;
    }

    @Override
    public int getCount() {
        return experimentMapper.getCount();
    }

    private int validateExperiment(ReqExperiment experiment) throws ApplicationErrorException {
        if(moduleMapper.selectByPrimaryKey(experiment.getModuleId()) == null){
            throw new ApplicationErrorException(ErrorCode.ModuleNotExists);
        }

        ImageTypeEnum imageType = ImageTypeEnum.fromInt(experiment.getImageType());
        if(imageType == null){
            throw new ApplicationErrorException(ErrorCode.InvalidImageType);
        }

        Integer imageId = experimentMapper.selectImageIdByTypeAndNameVersion(experiment.getImageType(), experiment.getImageNameVersion());
        if(imageId == null){
            throw new ApplicationErrorException(ErrorCode.ImageNotFound);
        }

        return imageId;
    }
}
