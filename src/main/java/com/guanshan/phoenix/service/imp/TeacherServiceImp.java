package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.EncryptionUtil;
import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.entity.StudentHomework;
import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.dao.entity.User;
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

import java.util.ArrayList;
import java.util.List;

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

        List<Clazz> classes = clazzMapper.selectByTeacherUserId(teacherUserId);
        for (Clazz clazz : classes) {
            ResClassDetail resClassDetailInfo = classService.getClassDetailInfo(clazz.getId());
            resTeacherClasses.add(resClassDetailInfo);
        }

        return resTeacherClassList;
    }

    @Override
    public void gradeHomework(ReqHomeworkGrade homeworkGrade) throws ApplicationErrorException {
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkById(
                homeworkGrade.getStudentHomeworkId());

        studentHomework.setComment(homeworkGrade.getComment());
        studentHomework.setScore(homeworkGrade.getGrade());

        studentHomeworkMapper.updateByPrimaryKey(studentHomework);
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
        Teacher teacher = teacherMapper.selectByTeacherNo(reqUpdateTeacher.getTeacherNo());
        if(teacher != null){
            throw new ApplicationErrorException(ErrorCode.TeacherAlreadyExists, teacher.getTno());
        }
        validateTeacher(reqUpdateTeacher);

        User newUser = managerService.createUser(reqUpdateTeacher.getTeacherNo(), RoleEnum.TEACHER);
        teacher = new Teacher();
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
        validateTeacher(reqUpdateTeacher);

        teacher.setTitle(reqUpdateTeacher.getTeacherTitleId());
        teacher.setEmail(reqUpdateTeacher.getTeacherContact());
        teacher.setTno(reqUpdateTeacher.getTeacherNo());
        teacher.setName(reqUpdateTeacher.getTeacherName());
        teacher.setGender(reqUpdateTeacher.getGender());
        teacherMapper.updateByUserId(teacher);
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
    public ResBatchAddTeacher batchTeacherCreation(MultipartFile file) throws ApplicationErrorException {
        ResBatchAddTeacher resBatchAddTeacher = new ResBatchAddTeacher();
        List<ResBatchAddTeacher.FailureReason> failureReasonList = new ArrayList<>();

        int success = 0;
        int failure = 0;

        ExcelTeacher excelTeacher = ExcelUtil.teacherExcelAnalysis(file);
        for (ExcelTeacher.ExcelTeacherElement excelTeacherElement : excelTeacher.getExcelTeacherElementList()) {
            try {
                User user = new User();
                user.setUsername(excelTeacherElement.getTeacherNum());
                user.setPassword(EncryptionUtil.encryptPassword(defaultPassword));
                user.setRole(RoleEnum.TEACHER.getCode());
                userMapper.insertSelective(user);

                Teacher teacher = new Teacher();
                teacher.setUserId(user.getId());
                teacher.setTno(excelTeacherElement.getTeacherNum());
                teacher.setName(excelTeacherElement.getTeacherName());
                teacher.setTitle(excelTeacherElement.getTeacherTitle());
                teacher.setGender(excelTeacherElement.getGender());
                teacher.setEmail(excelTeacherElement.getTeacherContact());
                teacherMapper.insertSelective(teacher);

                success += 1;
            } catch (Exception e) {
                ResBatchAddTeacher.FailureReason failureReason = new ResBatchAddTeacher().new FailureReason();
                failureReason.setTeacherNum(excelTeacherElement.getTeacherNum());
                // todo
                failureReason.setReason(ErrorCode.StudentAlreadyExists.getErrorStringFormat());
                failureReasonList.add(failureReason);

                failure += 1;
            }
        }
        resBatchAddTeacher.setSuccess(success);
        resBatchAddTeacher.setFailure(failure);
        resBatchAddTeacher.setFailureReasonList(failureReasonList);

        return resBatchAddTeacher;
    }

    private void validateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {

        TitleEnum title = TitleEnum.fromInt(reqUpdateTeacher.getTeacherTitleId());
        if(title == null){
            throw new ApplicationErrorException(ErrorCode.InvalidTitle);
        }

        Utility.validateEmail(reqUpdateTeacher.getTeacherContact());
    }
}
