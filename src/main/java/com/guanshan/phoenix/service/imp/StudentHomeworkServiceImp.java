package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.webdomain.ReqHomeworkSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class StudentHomeworkServiceImp implements StudentHomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentHomeworkMapper studentHomeworkMapper;

    @Autowired
    private CloudwareMapper cloudwareMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private StudentHomeworkResourceMapper studentHomeworkResourceMapper;

    @Override
    public StudentHomework getStudentHomeworkById(int studentHomeworkId) throws ApplicationErrorException {
        StudentHomework studentHomework = studentHomeworkMapper.selectByPrimaryKey(studentHomeworkId);

        if(studentHomework == null){
            throw new ApplicationErrorException(ErrorCode.StudentHomeworkNotExists);
        }

        return studentHomework;
    }

    @Override
    public void submitStudentHomework(ReqHomeworkSubmission homeworkSubmission) throws ApplicationErrorException {
        this.validStudentHomeWork(homeworkSubmission.getStudentId(), homeworkSubmission.getHomeworkId());
        StudentHomework studentHomework = studentHomeworkMapper.selectByStudentIdAndHomeworkId(
                homeworkSubmission.getStudentId(), homeworkSubmission.getHomeworkId());

        if(studentHomework == null){
            insertStudentHomeWork(homeworkSubmission);
        } else {
            updateStudentHomeWork(studentHomework, homeworkSubmission);
        }
    }

    @Override
    public void validStudentHomeWork(int studentId, int homeworkId) throws ApplicationErrorException {
        if(studentMapper.selectByPrimaryKey(studentId) == null){
            throw new ApplicationErrorException(ErrorCode.StudentNotExists);
        }

        if(homeworkMapper.selectByPrimaryKey(homeworkId) == null){
            throw new ApplicationErrorException(ErrorCode.HomeworkNotExists);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    protected void insertStudentHomeWork(ReqHomeworkSubmission homeworkSubmission){

        Cloudware cloudware = new Cloudware(
                homeworkSubmission.getCloudware_url(),
                homeworkSubmission.getCloudware_serviceId(),
                homeworkSubmission.getCloudware_instanceId());

        cloudwareMapper.insert(cloudware);

        StudentHomework studentHomework = new StudentHomework(
                homeworkSubmission.getStudentId(),
                homeworkSubmission.getHomeworkId(),
                cloudware.getId(),
                "",
                0,
                new Date(),
                new Date(),
                null,
                null
        );
        studentHomeworkMapper.insert(studentHomework);

        Resource resource = new Resource(
                "", homeworkSubmission.getHomework_url(), "", "");
        resourceMapper.insert(resource);

        StudentHomeworkResource studentHomeworkResource = new StudentHomeworkResource(
                studentHomework.getId(),
                resource.getId(),
                ResourceTypeEnum.HOMEWORK.getCode());
        studentHomeworkResourceMapper.insert(studentHomeworkResource);
    }

    @Transactional(rollbackFor = Throwable.class)
    protected void updateStudentHomeWork(StudentHomework studentHomework, ReqHomeworkSubmission homeworkSubmission){
        Cloudware cloudware = new Cloudware(
                studentHomework.getCloudwareId(),
                homeworkSubmission.getCloudware_url(),
                homeworkSubmission.getCloudware_serviceId(),
                homeworkSubmission.getCloudware_instanceId());
        cloudwareMapper.updateByPrimaryKey(cloudware);

        studentHomework.setLastEditDate(new Date());
        studentHomeworkMapper.updateByPrimaryKey(studentHomework);

        StudentHomeworkResource studentHomeworkResource =
                studentHomeworkResourceMapper.selectByPrimaryKeyAndType(
                        studentHomework.getId(), ResourceTypeEnum.HOMEWORK.getCode());

        Resource resource = new Resource(
                studentHomeworkResource.getResourceId(), "",
                homeworkSubmission.getHomework_url(), "", "");
        resourceMapper.updateByPrimaryKey(resource);
    }
}
