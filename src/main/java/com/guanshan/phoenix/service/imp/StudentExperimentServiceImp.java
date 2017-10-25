package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.StudentExperimentService;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.webdomain.request.ReqHomeworkSubmission;
import com.guanshan.phoenix.webdomain.request.ReqStudentExperimentCloudware;
import com.guanshan.phoenix.webdomain.request.ReqStudentHomeworkCloudware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentExperimentServiceImp implements StudentExperimentService {

    @Autowired
    private StudentExperimentMapper studentExperimentMapper;

    @Autowired
    private CloudwareMapper cloudwareMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Override
    public Cloudware getStudentExperimentCloudware(int experimentId, int studentId) throws ApplicationErrorException {
        StudentExperiment studentExperiment = studentExperimentMapper.selectByStudentIdAndExperimentId(studentId, experimentId);
        if(studentExperiment == null){
            throw new ApplicationErrorException(ErrorCode.StudentHomeworkNotExists);
        }

        return cloudwareMapper.selectByPrimaryKey(studentExperiment.getCloudwareId());
    }

    @Override
    public void createStudentExperimentCloudware(ReqStudentExperimentCloudware reqStudentExperimentCloudware) throws ApplicationErrorException {
        validateStudentExperiment(
                reqStudentExperimentCloudware.getStudentId(),
                reqStudentExperimentCloudware.getExperimentId()
        );
        StudentExperiment studentExperiment =
                studentExperimentMapper.selectByStudentIdAndExperimentId(reqStudentExperimentCloudware.getStudentId(),
                        reqStudentExperimentCloudware.getExperimentId());

        if(studentExperiment != null){
            throw new ApplicationErrorException(ErrorCode.StudentExperimentExists);
        }

        Cloudware cloudware = new Cloudware(reqStudentExperimentCloudware.getWebSocket(),
                reqStudentExperimentCloudware.getServiceId(),
                reqStudentExperimentCloudware.getInstanceId(),
                reqStudentExperimentCloudware.getServiceName(),
                reqStudentExperimentCloudware.getPulsarId());
        cloudwareMapper.insert(cloudware);

        studentExperiment = new StudentExperiment(
                reqStudentExperimentCloudware.getStudentId(),
                reqStudentExperimentCloudware.getExperimentId(),
                cloudware.getId()
        );
        studentExperimentMapper.insert(studentExperiment);
    }

    private void validateStudentExperiment(int studentId, int experimentId) throws ApplicationErrorException {

        if(experimentMapper.selectByPrimaryKey(experimentId) == null){
            throw new ApplicationErrorException(ErrorCode.ExperimentNotFound);
        }
    }
}
