package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Experiment;
import com.guanshan.phoenix.dao.entity.Module;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.service.CloudwareService;
import com.guanshan.phoenix.webdomain.request.ReqStudentExperiment;
import com.guanshan.phoenix.webdomain.response.ResExperimentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/19.
 */
@Service
public class CloudwareServiceImp implements CloudwareService {

    @Autowired
    private StudentExperimentMapper studentExperimentMapper;

    @Autowired
    private CloudwareMapper cloudwareMapper;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public String getStudentExperiment(ReqStudentExperiment reqStudentExperiment) throws ApplicationErrorException {

        int cloudwareId = studentExperimentMapper.selectCloudwareIdByStudentIdAndExperimentId(reqStudentExperiment.getStudentId(), reqStudentExperiment.getExperimentId());

        return cloudwareMapper.selectWebSocketById(cloudwareId);
    }

    @Override
    public ResExperimentInfo getExperiment(int id) throws ApplicationErrorException{


        Experiment experiment = experimentMapper.selectByPrimaryKey(id);
        Module module = moduleMapper.selectByPrimaryKey(experiment.getModuleId());
        Course course = courseMapper.selectByPrimaryKey(module.getCourseId());

        ResExperimentInfo resExperiment = new ResExperimentInfo(experiment);

        resExperiment.setCourseName(course.getName());
        resExperiment.setModuleName(module.getName());

        return resExperiment;
    }
}
