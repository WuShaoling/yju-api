package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.StudentExperimentService;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.webdomain.request.ReqHomeworkSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentExperimentServiceImp implements StudentExperimentService {

    @Autowired
    private StudentExperimentMapper studentExperimentMapper;

    @Autowired
    private CloudwareMapper cloudwareMapper;

    @Override
    public Cloudware getStudentExperimentCloudware(int experimentId, int studentId) throws ApplicationErrorException {
        StudentExperiment studentExperiment = studentExperimentMapper.selectByStudentIdAndExperimentId(studentId, experimentId);
        if(studentExperiment == null){
            throw new ApplicationErrorException(ErrorCode.StudentHomeworkNotExists);
        }

        return cloudwareMapper.selectByPrimaryKey(studentExperiment.getCloudwareId());
    }
}
