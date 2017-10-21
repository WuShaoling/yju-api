package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.CloudwareTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.HomeworkService;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.webdomain.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
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

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public ResHomeworkDetail getHomeworkDetail(int homeworkID) throws ApplicationErrorException {
        if(homeworkMapper.selectByPrimaryKey(homeworkID) == null){
            throw new ApplicationErrorException(ErrorCode.HomeworkNotExists);
        }

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
        CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(homework.getCloudwareType());
        homeworkDetail.setCloudwareType(cloudwareType == null ? "" : cloudwareType.toString());
        homeworkDetail.setDueDate(Utility.formatDate(homework.getDeadlineDate()));
        homeworkDetail.setPublishDate(Utility.formatDate(homework.getPublishDate()));

        return homeworkDetail;
    }

    @Override
    public ResHomeworkSubmissionList getAllHomeworkSubmissionByModuleId(int moduleId) throws ApplicationErrorException {
        if(moduleMapper.selectByPrimaryKey(moduleId) == null){
            throw new ApplicationErrorException(ErrorCode.ModuleNotExists);
        }

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
        homeworkDetail.setStudentId(student.getUserId());
        homeworkDetail.setStudentName(student.getName());
        homeworkDetail.setCloudwareUrl(studentHomework.getCloudwareUrl());
        homeworkDetail.setHomeworkUrl(studentHomework.getHomeworkUrl());

        return homeworkDetail;
    }

    @Override
    public int deleteHomework(int homeworkID) throws ApplicationErrorException {
        //todo: delete related student_homework and cloudware table
        homeworkMapper.deleteByPrimaryKey(homeworkID);
        return 0;
    }

    @Override
    public int updateHomework(ReqUpdateHomework reqUpdateHomework) throws ApplicationErrorException {
        Homework homework = new Homework();
        homework.setId(reqUpdateHomework.getHomeworkId());
        homework.setName(reqUpdateHomework.getHomeworkName());
        homework.setDescription(reqUpdateHomework.getHomeworkDes());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            homework.setPublishDate(sdf.parse(reqUpdateHomework.getHomeworkCreateDate()));
            homework.setDeadlineDate(sdf.parse(reqUpdateHomework.getHomeworkDueDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        homework.setCloudwareType(reqUpdateHomework.getCloudwareType());

        homeworkMapper.updateByPrimaryKeySelective(homework);
        return 0;
    }

    @Override
    public int createHomework(ReqCreateHomework reqCreateHomework) throws ApplicationErrorException {
        Homework homework = new Homework();
        homework.setClassId(reqCreateHomework.getClassId());
        homework.setModuleId(reqCreateHomework.getModuleId());
        homework.setName(reqCreateHomework.getHomeworkName());
        homework.setDescription(reqCreateHomework.getHomeworkDes());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            homework.setPublishDate(sdf.parse(reqCreateHomework.getHomeworkCreateDate()));
            homework.setDeadlineDate(sdf.parse(reqCreateHomework.getHomeworkDueDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        homework.setCloudwareType(reqCreateHomework.getCloudwareType());

        homeworkMapper.insertSelective(homework);
        return 0;
    }

    @Override
    public ResClassHomework getClassHomework(int classId) throws ApplicationErrorException {
        ResClassHomework resClassHomework = new ResClassHomework();

        Clazz clazz = clazzMapper.selectByPrimaryKey(classId);
        resClassHomework.setClassId(classId);
        resClassHomework.setClassName(clazz.getName());

        List<ResClassHomework.ResClassHomeworkModule> modules = new ArrayList<>();
        List<Module> moduleList = moduleMapper.selectByCourseID(clazz.getCourseId());
        for (Module module : moduleList) {
            ResClassHomework.ResClassHomeworkModule resClassHomeworkModule = new ResClassHomework().new ResClassHomeworkModule();
            resClassHomeworkModule.setModuleId(module.getId());
            resClassHomeworkModule.setModuleName(module.getName());

            List<ResClassHomework.ResClassHomeworkModuleHomework> homeworks =  new ArrayList<>();
            List<Homework> homeworkList = homeworkMapper.selectByModuleId(module.getId());
            for (Homework homework : homeworkList) {
                ResClassHomework.ResClassHomeworkModuleHomework resClassHomeworkModuleHomework = new ResClassHomework().new ResClassHomeworkModuleHomework();
                resClassHomeworkModuleHomework.setHomeworkId(homework.getId());
                resClassHomeworkModuleHomework.setHomeworkName(homework.getName());
                resClassHomeworkModuleHomework.setHomeworkDes(homework.getDescription());
                resClassHomeworkModuleHomework.setHomeworkCreateDate(homework.getPublishDate().toString());
                resClassHomeworkModuleHomework.setHomeworkDueDate(homework.getDeadlineDate().toString());
                CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(homework.getCloudwareType());
                resClassHomeworkModuleHomework.setCloudwareType(cloudwareType == null ? "" : cloudwareType.toString());

                homeworks.add(resClassHomeworkModuleHomework);
            }
            resClassHomeworkModule.setHomeworks(homeworks);
            modules.add(resClassHomeworkModule);
        }
        resClassHomework.setModules(modules);

        return resClassHomework;
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
            submissionDetails.add(submissionDetail);

            submissionDetail.setHomeworkId(homework.getId());
            submissionDetail.setStudentId(student.getUserId());
            submissionDetail.setStudentName(student.getName());
            submissionDetail.setDueDate(Utility.formatDate(homework.getDeadlineDate()));

            StudentHomework studentHomework =
                studentHomeworkMapper.selectByStudentIdAndHomeworkId(student.getUserId(), homework.getId());

            if(studentHomework == null){
                submissionDetail.setStudentHomeworkId(0);
                submissionDetail.setCompleted(false);
            }else{
                submissionDetail.setStudentHomeworkId(studentHomework.getId());
                submissionDetail.setCompleted(true);
                submissionDetail.setSubmissionDate(Utility.formatDate(studentHomework.getSubmissionDate()));
                submissionDetail.setLastEditDate(Utility.formatDate(studentHomework.getLastEditDate()));
            }
        }

        return submissionDetails;
    }
}
