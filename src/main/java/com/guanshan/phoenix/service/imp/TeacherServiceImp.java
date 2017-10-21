package com.guanshan.phoenix.service.imp;

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
import com.guanshan.phoenix.service.*;
import com.guanshan.phoenix.webdomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {
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
    @Transactional(rollbackFor = Throwable.class)
    public void createTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {
        validateTeacher(reqUpdateTeacher);

        User newUser = managerService.createUser(reqUpdateTeacher.getTeacherName(), RoleEnum.TEACHER);
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
    @Transactional
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

        User user = userMapper.selectByPrimaryKey(teacher.getUserId());
        user.setUsername(teacher.getName());
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteTeacherByTeacherUserId(int teacherId) throws ApplicationErrorException {
        Teacher teacher = teacherMapper.selectByUserId(teacherId);
        if(teacher == null)
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);

        if(courseMapper.isTeacherUsedByCourse(teacherId)){
            throw new ApplicationErrorException(ErrorCode.TeacherIsUsedByCourse);
        }

        teacherMapper.deleteByUserId(teacherId);
        userService.deleteUserById(teacher.getUserId());
    }

    private void validateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {
        Teacher teacher = teacherMapper.selectByTeacherNo(reqUpdateTeacher.getTeacherNo());

        if(teacher != null){
            throw new ApplicationErrorException(ErrorCode.TeacherAlreadyExists, teacher.getTno());
        }

        TitleEnum title = TitleEnum.fromInt(reqUpdateTeacher.getTeacherTitleId());
        if(title == null){
            throw new ApplicationErrorException(ErrorCode.InvalidTitle);
        }

        Utility.validateEmail(reqUpdateTeacher.getTeacherContact());
    }
}
