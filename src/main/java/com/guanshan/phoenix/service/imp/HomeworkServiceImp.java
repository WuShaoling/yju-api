package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.CloudwareTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.HomeworkService;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.webdomain.ResHomeworkDetail;
import com.guanshan.phoenix.webdomain.ResHomeworkSubmissionList;
import com.guanshan.phoenix.webdomain.ResStudentHomeworkDetail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HomeworkServiceImp implements HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentHomeworkMapper studentHomeworkMapper;

    @Autowired
    private StudentHomeworkService studentHomeworkService;

    @Override
    public ResHomeworkDetail getHomeworkDetail(int homeworkID) throws ApplicationErrorException {
        ResHomeworkDetail homeworkDetail = new ResHomeworkDetail();

        Homework homework = homeworkMapper.selectByPrimaryKey(homeworkID);
        Module module = moduleMapper.selectByPrimaryKey(homework.getModuleId());
        Course course = courseMapper.selectByPrimaryKey(module.getCourseId());

        if(homework == null){
            throw new ApplicationErrorException(ErrorCode.HomeworkNotExists);
        }

        homeworkDetail.setCourseName(course.getName());
        homeworkDetail.setModuleName(module.getName());
        homeworkDetail.setHomeworkName(homework.getName());
        homeworkDetail.setHomeworkDes(homework.getDescription());
        homeworkDetail.setClassId(homework.getClassId());
        homeworkDetail.setCloudwareType(CloudwareTypeEnum.fromInt(homework.getCloudwareType()).toString());
        homeworkDetail.setDueDate(homework.getDeadlineDate().toString());
        homeworkDetail.setPublishDate(homework.getPublishDate().toString());

        return homeworkDetail;
    }

    @Override
    public ResHomeworkSubmissionList getAllHomeworkSubmissionByModuleId(int moduleId) {
        List<Homework> homeworks = homeworkMapper.selectByModuleId(moduleId);
        ResHomeworkSubmissionList submissionList = new ResHomeworkSubmissionList();
        List<ResHomeworkSubmissionList.ResHomeworkSubmissionDetail> submissionDetails =
                new ArrayList<>();
        submissionList.setHomeworkSubmissionList(submissionDetails);

        for(Homework homework : homeworks){
            submissionDetails.addAll(this.getHomeworkSubmissionDetail(homework));
        }

        return submissionList;
    }

    @Override
    public ResStudentHomeworkDetail getStudentHomeworkDetailById(int studentHomeworkId) throws ApplicationErrorException {
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkById(studentHomeworkId);

        ResStudentHomeworkDetail homeworkDetail = new ResStudentHomeworkDetail();

        Homework homework = homeworkMapper.selectByPrimaryKey(studentHomework.getHomeworkId());
        Module module = moduleMapper.selectByPrimaryKey(homework.getModuleId());
        Course course = courseMapper.selectByPrimaryKey(module.getCourseId());
        Student student = studentMapper.selectByPrimaryKey(studentHomework.getStudentId());

        homeworkDetail.setCourseName(course.getName());
        homeworkDetail.setModuleName(module.getName());
        homeworkDetail.setHomeworkName(homework.getName());
        homeworkDetail.setStudentId(student.getId());
        homeworkDetail.setStudentName(student.getName());
        homeworkDetail.setCloudwareUrl(studentHomework.getCloudwareUrl());
        homeworkDetail.setHomeworkUrl(studentHomework.getHomeworkUrl());

        return homeworkDetail;
    }

    private List<ResHomeworkSubmissionList.ResHomeworkSubmissionDetail> getHomeworkSubmissionDetail(
            Homework homework
    ){
        List<ResHomeworkSubmissionList.ResHomeworkSubmissionDetail> submissionDetails =
                new ArrayList<>();

        List<Student> studentsInClass = studentMapper.selectByClassId(homework.getClassId());

        for (Student student : studentsInClass){
            ResHomeworkSubmissionList.ResHomeworkSubmissionDetail submissionDetail =
                    new ResHomeworkSubmissionList.ResHomeworkSubmissionDetail();

            submissionDetail.setHomeworkId(homework.getId());
            submissionDetail.setStudentId(student.getId());
            submissionDetail.setStudentName(student.getName());
            submissionDetail.setDueDate(homework.getDeadlineDate().toString());

            StudentHomework studentHomework =
                studentHomeworkMapper.selectByStudentIdAndHomeworkId(student.getId(), homework.getId());

            if(studentHomework == null){
                submissionDetail.setStudentHomeworkId(0);
                submissionDetail.setCompleted(false);
            }else{
                submissionDetail.setStudentHomeworkId(studentHomework.getId());
                submissionDetail.setCompleted(true);
                submissionDetail.setSubmissionDate(studentHomework.getSubmissionDate().toString());
                submissionDetail.setLastEditDate(studentHomework.getLastEditDate().toString());
            }
        }

        return submissionDetails;
    }
}
