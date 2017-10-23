package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.CloudwareTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.HomeworkService;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.webdomain.request.ReqCreateHomework;
import com.guanshan.phoenix.webdomain.request.ReqDeleteHomework;
import com.guanshan.phoenix.webdomain.request.ReqUpdateHomework;
import com.guanshan.phoenix.webdomain.response.ResClassHomework;
import com.guanshan.phoenix.webdomain.response.ResHomeworkDetail;
import com.guanshan.phoenix.webdomain.response.ResHomeworkSubmissionList;
import com.guanshan.phoenix.webdomain.response.ResStudentHomeworkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private HomeworkResourceMapper homeworkResourceMapper;

    @Autowired
    private TeacherMapper teacherMapper;

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
    public int deleteHomework(ReqDeleteHomework reqDeleteHomework) throws ApplicationErrorException {
        int homeworkID = reqDeleteHomework.getHomeworkId();

        Homework homework = homeworkMapper.selectByPrimaryKey(homeworkID);
        if (homework == null) {
            throw new ApplicationErrorException(ErrorCode.HomeworkNotExists);
        }

        if (studentHomeworkMapper.isHomeworkUsedByStudentHomework(homeworkID)) {
            throw new ApplicationErrorException(ErrorCode.HomeworkUsedByStudentHomework);
        }

        HomeworkResource homeworkResource = homeworkResourceMapper.selectByHomeworkId(homeworkID);
        if (homeworkResource != null) {
            homeworkResourceMapper.deleteByPrimaryKey(homeworkResource.getId());
            resourceMapper.deleteByPrimaryKey(homeworkResource.getResourceId());
        }
        homeworkMapper.deleteByPrimaryKey(homeworkID);
        return 0;
    }

    @Override
    public int updateHomework(ReqUpdateHomework reqUpdateHomework) throws ApplicationErrorException {
        Homework homework = homeworkMapper.selectByPrimaryKey(reqUpdateHomework.getHomeworkId());

        if(homework == null){
            throw new ApplicationErrorException(ErrorCode.HomeworkNotExists);
        }

        homework.setName(reqUpdateHomework.getHomeworkName());
        homework.setDescription(reqUpdateHomework.getHomeworkDes());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            homework.setPublishDate(Utility.parseShortDate(reqUpdateHomework.getHomeworkCreateDate()));
            homework.setDeadlineDate(Utility.parseShortDate((reqUpdateHomework.getHomeworkDueDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        homework.setCloudwareType(reqUpdateHomework.getCloudwareType());

        validate(homework);

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

        try {
            homework.setPublishDate(Utility.parseShortDate(reqCreateHomework.getHomeworkCreateDate()));
            homework.setDeadlineDate(Utility.parseShortDate((reqCreateHomework.getHomeworkDueDate())));
        } catch (ParseException e) {
            //swallow the parse exception
            e.printStackTrace();
        }
        homework.setCloudwareType(reqCreateHomework.getCloudwareType());

        validate(homework);

        homeworkMapper.insertSelective(homework);
        return 0;
    }

    @Override
    public ResClassHomework getClassHomework(int classId) throws ApplicationErrorException {
        if(clazzMapper.selectByPrimaryKey(classId) == null){
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        ResClassHomework resClassHomework = new ResClassHomework();

        Clazz clazz = clazzMapper.selectByPrimaryKey(classId);
        Teacher teacher = teacherMapper.selectByClassId(classId);
        resClassHomework.setClassId(classId);
        resClassHomework.setClassName(clazz.getName());

        List<ResClassHomework.ResClassHomeworkModule> modules = new ArrayList<>();
        List<Module> moduleList = moduleMapper.selectByCourseID(clazz.getCourseId());
        for (Module module : moduleList) {
            ResClassHomework.ResClassHomeworkModule resClassHomeworkModule = new ResClassHomework().new ResClassHomeworkModule();
            resClassHomeworkModule.setModuleId(module.getId());
            resClassHomeworkModule.setModuleName(module.getName());

            List<ResClassHomework.ResClassHomeworkModuleHomework> homeworks =  new ArrayList<>();
            List<Homework> homeworkList = homeworkMapper.selectByModuleIdAndClassId(module.getId(), classId);
            for (Homework homework : homeworkList) {
                ResClassHomework.ResClassHomeworkModuleHomework resClassHomeworkModuleHomework = new ResClassHomework().new ResClassHomeworkModuleHomework();
                resClassHomeworkModuleHomework.setHomeworkId(homework.getId());
                resClassHomeworkModuleHomework.setHomeworkName(homework.getName());
                resClassHomeworkModuleHomework.setHomeworkDes(homework.getDescription());
                resClassHomeworkModuleHomework.setHomeworkCreateDate(Utility.formatDate(homework.getPublishDate()));
                resClassHomeworkModuleHomework.setTeacherName(teacher.getName());
                resClassHomeworkModuleHomework.setHomeworkDueDate(Utility.formatDate(homework.getDeadlineDate()));
                CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(homework.getCloudwareType());
                resClassHomeworkModuleHomework.setCloudwareType(cloudwareType == null ? "" : cloudwareType.toString());

                homeworks.add(resClassHomeworkModuleHomework);
            }
            if(homeworks.size() > 0) {
                resClassHomeworkModule.setHomeworks(homeworks);
                modules.add(resClassHomeworkModule);
            }
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

    private void validate(Homework homework) throws ApplicationErrorException {
        CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(homework.getCloudwareType());

        if(cloudwareType == null){
            throw new ApplicationErrorException(ErrorCode.InvalidCloudwareType);
        }

        Clazz clazz = clazzMapper.selectByPrimaryKey(homework.getClassId());
        if(clazz == null){
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }
        Module module = moduleMapper.selectByPrimaryKey(homework.getModuleId());
        if(module == null){
            throw new ApplicationErrorException(ErrorCode.ModuleNotExists);
        }

        if(module.getCourseId() != clazz.getCourseId()){
            throw new ApplicationErrorException(ErrorCode.HomeworkModuleClassBelongsToDifferentCourse);
        }
    }
}
