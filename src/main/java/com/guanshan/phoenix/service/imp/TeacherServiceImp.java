package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.EncryptionUtil;
import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.RoleEnum;
import com.guanshan.phoenix.enums.TitleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.excel.ExcelUtil;
import com.guanshan.phoenix.excel.domain.ExcelTeacher;
import com.guanshan.phoenix.service.*;
import com.guanshan.phoenix.webdomain.request.ReqDeleteTeacher;
import com.guanshan.phoenix.webdomain.request.ReqHomeworkGrade;
import com.guanshan.phoenix.webdomain.request.ReqUpdateTeacher;
import com.guanshan.phoenix.webdomain.response.ResBatchAddTeacher;
import com.guanshan.phoenix.webdomain.response.ResClassDetail;
import com.guanshan.phoenix.webdomain.response.ResTeacherClassList;
import com.guanshan.phoenix.webdomain.response.ResTeacherList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImp implements TeacherService {

    @Value("${default.password}")
    private String defaultPassword;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private ClassService classService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentHomeworkMapper studentHomeworkMapper;

    @Autowired
    private StudentHomeworkService studentHomeworkService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CloudwareService cloudwareService;

    @Override
    public Teacher getTeacherByUserId(int teacherID) throws ApplicationErrorException {

        Teacher teacher = teacherMapper.selectByUserId(teacherID);
        if(teacher == null){
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);
        }

        return teacher;
    }

    @Override
    public ResTeacherClassList getAllTeacherClassInfoByUserId(int teacherUserId) throws ApplicationErrorException {
        if(teacherMapper.selectByUserId(teacherUserId) == null){
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);
        }

        ResTeacherClassList resTeacherClassList = new ResTeacherClassList();
        List<ResClassDetail> resTeacherClasses = new ArrayList<>();
        resTeacherClassList.setTeacherClassList(resTeacherClasses);

        for (Map clazzInfo : clazzMapper.selectAllClassInfoByTeacherUserId(teacherUserId)) {
            ResClassDetail resClassDetailInfo = new ResClassDetail();
            resTeacherClasses.add(resClassDetailInfo);

            resClassDetailInfo.setClassId((int)clazzInfo.get("classId"));
            resClassDetailInfo.setClassName((String) clazzInfo.get("className"));
            resClassDetailInfo.setTerm(new Term(
                    (String) clazzInfo.get("year"),
                    (int) clazzInfo.get("semester")
            ).getDescription());
            resClassDetailInfo.setImage((String) clazzInfo.get("url"));
            resClassDetailInfo.setDuration((String) clazzInfo.get("duration"));
            resClassDetailInfo.setStudentNumber((int) clazzInfo.get("studentNum"));
            resClassDetailInfo.setCourseDescription((String) clazzInfo.get("description"));
            resClassDetailInfo.setCourseId((int) clazzInfo.get("courseId"));
            resClassDetailInfo.setCourseName((String) clazzInfo.get("courseName"));
            resClassDetailInfo.setTeacherContract((String) clazzInfo.get("email"));
            resClassDetailInfo.setTeacherName((String) clazzInfo.get("teacherName"));
            resClassDetailInfo.setClassDate(Utility.formatDate((Date) clazzInfo.get("classDate")));
        }

        return resTeacherClassList;
    }

    @Override
    public void gradeHomework(ReqHomeworkGrade homeworkGrade) throws ApplicationErrorException {
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkById(
                homeworkGrade.getStudentHomeworkId());

        if(studentHomework == null){
            throw new ApplicationErrorException(ErrorCode.StudentHomeworkNotExists);
        }

        Integer cloudwareId = studentHomework.getCloudwareId();
        studentHomework.setComment(homeworkGrade.getComment());
        studentHomework.setScore(homeworkGrade.getGrade());
        studentHomework.setCloudwareId(null);
        studentHomeworkMapper.updateByPrimaryKey(studentHomework);

        if(cloudwareId != null){
            cloudwareService.deleteCloudware(cloudwareId);
        }
    }

    @Override
    public ResTeacherList getAllTeacherList() throws ApplicationErrorException {
        ResTeacherList teacherList = new ResTeacherList();
        List<ResTeacherList.ResTeacherInfo> teacherInfoList = new ArrayList<>();
        teacherList.setTeacherInfoList(teacherInfoList);

        for (Teacher teacher : teacherMapper.getAllTeachers()){
            ResTeacherList.ResTeacherInfo teacherInfo = new ResTeacherList.ResTeacherInfo(teacher);
            teacherInfoList.add(teacherInfo);
        }

        return teacherList;
    }

    @Override
    public void createTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {
        User user = userMapper.selectByUserName(reqUpdateTeacher.getTeacherNo());
        if(user != null){
            throw new ApplicationErrorException(ErrorCode.TeacherAlreadyExists, user.getUsername());
        }
        validateTeacher(reqUpdateTeacher);

        User newUser = managerService.createUser(reqUpdateTeacher.getTeacherNo(), RoleEnum.TEACHER);
        Teacher teacher = new Teacher();
        teacher.setUserId(newUser.getId());
        teacher.setTno(reqUpdateTeacher.getTeacherNo());
        teacher.setName(reqUpdateTeacher.getTeacherName());
        teacher.setTitle(reqUpdateTeacher.getTeacherTitleId());
        teacher.setGender(reqUpdateTeacher.getGender());
        teacher.setEmail(reqUpdateTeacher.getTeacherContact());

        teacherMapper.insert(teacher);
    }

    @Override
    public void updateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {
        Teacher teacher = teacherMapper.selectByUserId(reqUpdateTeacher.getId());

        if(teacher == null){
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);
        }
        if( !teacher.getTno().equals(reqUpdateTeacher.getTeacherNo())){
            //If teacher No is changed
            if(userMapper.selectByUserName(reqUpdateTeacher.getTeacherNo()) != null){
                throw new ApplicationErrorException(ErrorCode.TeacherAlreadyExists, reqUpdateTeacher.getTeacherNo());
            }
        }

        validateTeacher(reqUpdateTeacher);

        teacher.setTitle(reqUpdateTeacher.getTeacherTitleId());
        teacher.setEmail(reqUpdateTeacher.getTeacherContact());
        teacher.setTno(reqUpdateTeacher.getTeacherNo());
        teacher.setName(reqUpdateTeacher.getTeacherName());
        teacher.setGender(reqUpdateTeacher.getGender());
        teacherMapper.updateByUserId(teacher);

        User user = userMapper.selectByPrimaryKey(teacher.getUserId());
        user.setUsername(reqUpdateTeacher.getTeacherNo());
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteTeacherByTeacherUserId(ReqDeleteTeacher reqDeleteTeacher) throws ApplicationErrorException {
        int teacherId = reqDeleteTeacher.getTeacherId();

        Teacher teacher = teacherMapper.selectByUserId(teacherId);
        if (teacher == null)
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);

        if (courseMapper.isTeacherUsedByCourse(teacherId)) {
            throw new ApplicationErrorException(ErrorCode.TeacherIsUsedByCourse);
        }

        teacherMapper.deleteByUserId(teacherId);
        userService.deleteUserById(teacher.getUserId());
    }

    @Override
    public ResBatchAddTeacher batchTeacherCreation(MultipartFile file) throws ApplicationErrorException, IOException {
        ResBatchAddTeacher resBatchAddTeacher = new ResBatchAddTeacher();
        List<ResBatchAddTeacher.FailureReason> failureReasonList = new ArrayList<>();

        int success = 0;
        int failure = 0;

        ExcelTeacher excelTeacher = ExcelUtil.teacherExcelAnalysis(file);
        for (ExcelTeacher.ExcelTeacherElement excelTeacherElement : excelTeacher.getExcelTeacherElementList()) {
            try {
                ReqUpdateTeacher updateTeacher = new ReqUpdateTeacher();
                updateTeacher.setTeacherNo(excelTeacherElement.getTeacherNum());
                updateTeacher.setTeacherName(excelTeacherElement.getTeacherName());
                updateTeacher.setGender(excelTeacherElement.getGender());
                updateTeacher.setTeacherTitleId(excelTeacherElement.getTeacherTitle());
                updateTeacher.setTeacherContact(excelTeacherElement.getTeacherContact());
                this.createTeacher(updateTeacher);

                success += 1;
            } catch (ApplicationErrorException e) {
                ResBatchAddTeacher.FailureReason failureReason = new ResBatchAddTeacher().new FailureReason();
                failureReason.setTeacherNum(excelTeacherElement.getTeacherNum());
                // todo
                failureReason.setReason(e.getMessage());
                failureReasonList.add(failureReason);

                failure += 1;
            }
        }
        resBatchAddTeacher.setSuccess(success);
        resBatchAddTeacher.setFailure(failure);
        resBatchAddTeacher.setFailureReasonList(failureReasonList);

        return resBatchAddTeacher;
    }

    @Override
    public int getCount() {
        return teacherMapper.getCount();
    }

    private void validateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {

        TitleEnum title = TitleEnum.fromInt(reqUpdateTeacher.getTeacherTitleId());
        if(title == null){
            throw new ApplicationErrorException(ErrorCode.InvalidTitle);
        }

        //Utility.validateEmail(reqUpdateTeacher.getTeacherContact());
    }
}
