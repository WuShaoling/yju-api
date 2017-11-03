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
import com.guanshan.phoenix.webdomain.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeworkServiceImp implements HomeworkService {

    @Value("${notice.limit}")
    private int noticeLimit;

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

    @Autowired
    private CloudwareMapper cloudwareMapper;

    @Autowired
    private StudentClassMapper studentClassMapper;

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
    public ResHomeworkSubmissionList getAllHomeworkSubmissionByModuleId(int moduleId, int classId) throws ApplicationErrorException {
        Module module = moduleMapper.selectByPrimaryKey(moduleId);
        Clazz clazz = clazzMapper.selectByPrimaryKey(classId);
        if(module == null){
            throw new ApplicationErrorException(ErrorCode.ModuleNotExists);
        }
        if(clazz == null){
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        Course course = courseMapper.selectByPrimaryKey(module.getCourseId());
        List<Homework> homeworks = homeworkMapper.selectByModuleIdAndClassId(moduleId, classId);
        ResHomeworkSubmissionList submissionList = new ResHomeworkSubmissionList();
        List<ResHomeworkSubmissionList.ResHomeworkList> homeworkList =
                new ArrayList<>();
        submissionList.setHomeworkList(homeworkList);
        submissionList.setModuleName(module.getName());
        submissionList.setCourseName(course.getName());
        submissionList.setClassName(clazz.getName());

        for(Homework homework : homeworks){
            homeworkList.add(this.getHomeworkSubmissionDetail(homework));
        }

        return submissionList;
    }

    @Override
    public ResStudentHomeworkDetail getStudentHomeworkDetailByHomeworkIdAndStudentId(int homeworkId, int studentId) throws ApplicationErrorException {
        StudentHomework studentHomework = studentHomeworkMapper.selectByStudentIdAndHomeworkId(studentId, homeworkId);

        if(studentHomework == null){
            throw new ApplicationErrorException(ErrorCode.StudentHomeworkNotExists);
        }

        return getStudentHomeworkDetailById(studentHomework.getId());
    }

    @Override
    public ResStudentHomeworkDetail getStudentHomeworkDetailById(int studentHomeworkId) throws ApplicationErrorException {
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkById(studentHomeworkId);

        ResStudentHomeworkDetail homeworkDetail = new ResStudentHomeworkDetail();

        Homework homework = homeworkMapper.selectByPrimaryKey(studentHomework.getHomeworkId());
        Module module = moduleMapper.selectByPrimaryKey(homework.getModuleId());
        Course course = courseMapper.selectByPrimaryKey(module.getCourseId());
        Student student = studentMapper.selectByUserId(studentHomework.getStudentId());

        homeworkDetail.setCourseName(course.getName());
        homeworkDetail.setModuleName(module.getName());
        homeworkDetail.setHomeworkName(homework.getName());
        homeworkDetail.setStudentId(student.getUserId());
        homeworkDetail.setStudentName(student.getName());
        homeworkDetail.setCloudwareUrl(studentHomework.getCloudwareUrl());
        homeworkDetail.setHomeworkUrl(studentHomework.getHomeworkUrl());
        homeworkDetail.setScore(studentHomework.getScore());
        homeworkDetail.setComment(studentHomework.getComment());

        return homeworkDetail;
    }

    @Override
    public int deleteHomework(ReqDeleteHomework reqDeleteHomework) throws ApplicationErrorException {
        int homeworkID = reqDeleteHomework.getHomeworkId();

        Homework homework = homeworkMapper.selectByPrimaryKey(homeworkID);
        if (homework == null) {
            throw new ApplicationErrorException(ErrorCode.HomeworkNotExists);
        }

//        if (studentHomeworkMapper.isHomeworkUsedByStudentHomework(homeworkID)) {
//            throw new ApplicationErrorException(ErrorCode.HomeworkUsedByStudentHomework);
//        }

        for (Integer studentHomeworkId : studentHomeworkMapper.selectStudentHomeworkByHomeworkId(homeworkID)){
            studentHomeworkService.deleteStudentHomework(studentHomeworkId);
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
        if (reqUpdateHomework.getHomeworkCreateDate() != null) {
            homework.setPublishDate(reqUpdateHomework.getHomeworkCreateDate());
        }
        if (reqUpdateHomework.getHomeworkDueDate() != null) {
            homework.setDeadlineDate(reqUpdateHomework.getHomeworkDueDate());
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
        if (reqCreateHomework.getHomeworkCreateDate() != null) {
            homework.setPublishDate(reqCreateHomework.getHomeworkCreateDate());
        }
        if (reqCreateHomework.getHomeworkDueDate() != null) {
            homework.setDeadlineDate(reqCreateHomework.getHomeworkDueDate());
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
        Course course = courseMapper.selectByPrimaryKey(clazz.getCourseId());
        Teacher teacher = teacherMapper.selectByClassId(classId);
        resClassHomework.setClassId(classId);
        resClassHomework.setClassName(clazz.getName());
        resClassHomework.setCourseName(course.getName());

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

            resClassHomeworkModule.setHomeworks(homeworks);
            modules.add(resClassHomeworkModule);

        }
        resClassHomework.setModules(modules);

        return resClassHomework;
    }

    @Override
    public ResStudentHomeworkList getStudentHomeworkListById(int studentId) throws ApplicationErrorException {
        ResStudentHomeworkList resStudentHomeworkList = new ResStudentHomeworkList();
        List<ResStudentHomeworkList.ResHomework> resHomeworkList = new ArrayList<>();

        List<StudentClass> studentClassList = studentClassMapper.selectByStudentUserId(studentId);
        if (studentClassList == null) {
            throw new ApplicationErrorException(ErrorCode.StudentNotInClass);
        }

        List<Integer> classIds = new ArrayList<>();
        for (StudentClass studentClass : studentClassList) {
            classIds.add(studentClass.getClassId());
        }

        List<Homework> homeworkList = new ArrayList<>();
        for (Integer classId : classIds) {
            homeworkList.addAll(homeworkMapper.selectByClassId(classId));
        }

        for (Homework homework : homeworkList) {
            ResStudentHomeworkList.ResHomework resHomework = new ResStudentHomeworkList().new ResHomework();
            resHomework.setId(homework.getId());
            resHomework.setName(homework.getName());
            resHomework.setDescription(homework.getDescription());
            CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(homework.getCloudwareType());
            resHomework.setCloudwareType(cloudwareType == null ? "" : cloudwareType.toString());
            resHomework.setPublishDate(Utility.formatDate(homework.getPublishDate()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(homework.getPublishDate());
            resHomework.setPublishMonth(calendar.get(Calendar.MONTH) + 1);
            resHomework.setPublishDay(calendar.get(Calendar.DATE));
            resHomework.setDeadlineDate(Utility.formatDate(homework.getDeadlineDate()));
            resHomework.setClassId(homework.getClassId());
            resHomework.setClassName(clazzMapper.selectByPrimaryKey(homework.getClassId()).getName());
            resHomework.setTeacherName(teacherMapper.selectByClassId(homework.getClassId()).getName());
            resHomework.setComplete(false);

            resHomeworkList.add(resHomework);
        }

        List<StudentHomework> studentHomeworkList = studentHomeworkMapper.selectByStudentId(studentId);
        for (StudentHomework studentHomework: studentHomeworkList) {
            for (ResStudentHomeworkList.ResHomework resHomework : resHomeworkList) {
                if (studentHomework.getHomeworkId().equals(resHomework.getId())) {
                    resHomework.setComplete(true);
                }
            }
        }

        Collections.sort(resHomeworkList, new Comparator<ResStudentHomeworkList.ResHomework>() {
            @Override
            public int compare(ResStudentHomeworkList.ResHomework o1, ResStudentHomeworkList.ResHomework o2) {
                return o2.getPublishDate().compareTo(o1.getPublishDate());
            }
        });
        
        resStudentHomeworkList.setResHomeworkList(resHomeworkList);
        return resStudentHomeworkList;
    }

    @Override
    public ResTeacherHomeworkList getHomeworkListByTeacherId(int teacherId) throws ApplicationErrorException {
        ResTeacherHomeworkList resTeacherHomeworkList = new ResTeacherHomeworkList();
        List<ResTeacherHomeworkList.ResHomework> resHomeworkList = new ArrayList<>();


        List<Course> courseList = courseMapper.selectByTeacherId(teacherId);
        if (courseList == null) {
            throw new ApplicationErrorException(ErrorCode.TeacherIsNotInClass);
        }

        List<Clazz> clazzLists = new ArrayList<>();
        for (Course course : courseList) {
            List<Clazz> clazzList = clazzMapper.selectByCourseId(course.getId());
            clazzLists.addAll(clazzList);
        }

        for (Clazz clazz : clazzLists) {
            for (Homework homework : homeworkMapper.selectByClassId(clazz.getId())) {
                ResTeacherHomeworkList.ResHomework resHomework = new ResTeacherHomeworkList().new ResHomework();
                resHomework.setClassId(clazz.getId());
                resHomework.setClassName(clazz.getName());
                resHomework.setName(homework.getName());
                resHomework.setDescription(homework.getDescription());
                CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(homework.getCloudwareType());
                resHomework.setCloudwareType(cloudwareType == null ? "" : cloudwareType.toString());
                resHomework.setPublishDate(Utility.formatDate(homework.getPublishDate()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(homework.getPublishDate());
                resHomework.setPublishMonth(calendar.get(Calendar.MONTH) + 1);
                resHomework.setPublishDay(calendar.get(Calendar.DATE));
                resHomework.setDeadlineDate(Utility.formatDate(homework.getDeadlineDate()));

                resHomeworkList.add(resHomework);
            }
        }

        Collections.sort(resHomeworkList, new Comparator<ResTeacherHomeworkList.ResHomework>() {
            @Override
            public int compare(ResTeacherHomeworkList.ResHomework o1, ResTeacherHomeworkList.ResHomework o2) {
                return o2.getPublishDate().compareTo(o1.getPublishDate());
            }
        });
        resTeacherHomeworkList.setResHomeworkList(resHomeworkList);
        return resTeacherHomeworkList;
    }

    private ResHomeworkSubmissionList.ResHomeworkList getHomeworkSubmissionDetail(
            Homework homework
    ){
        ResHomeworkSubmissionList.ResHomeworkList resHomeworkList = new ResHomeworkSubmissionList.ResHomeworkList();
        resHomeworkList.setHomeworkId(homework.getId());
        resHomeworkList.setHomeworkName(homework.getName());
        CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(homework.getCloudwareType());
        resHomeworkList.setCloudwareType(cloudwareType == null ? "" : cloudwareType.toString());

        List<ResHomeworkSubmissionList.ResHomeworkSubmissionDetail> submissionDetails =
                new ArrayList<>();
        resHomeworkList.setHomeworkSubmissionList(submissionDetails);

        List<Student> studentsInClass = studentMapper.selectByClassId(homework.getClassId());
        int completed = 0;

        for (Student student : studentsInClass){
            ResHomeworkSubmissionList.ResHomeworkSubmissionDetail submissionDetail =
                    new ResHomeworkSubmissionList.ResHomeworkSubmissionDetail();
            submissionDetails.add(submissionDetail);

            submissionDetail.setHomeworkId(homework.getId());
            submissionDetail.setStudentId(student.getUserId());
            submissionDetail.setStudentNo(student.getSno());
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
                submissionDetail.setScore(studentHomework.getScore());
                completed++;
            }
        }

        resHomeworkList.setCompletedCount(completed);
        resHomeworkList.setNonCompletedCount(studentsInClass.size() - completed);
        return resHomeworkList;
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
