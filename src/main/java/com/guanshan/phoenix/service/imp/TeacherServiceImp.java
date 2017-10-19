package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.entity.StudentHomework;
import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.dao.entity.User;
import com.guanshan.phoenix.dao.mapper.ClazzMapper;
import com.guanshan.phoenix.dao.mapper.StudentHomeworkMapper;
import com.guanshan.phoenix.dao.mapper.TeacherMapper;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.enums.GenderEnum;
import com.guanshan.phoenix.enums.TitleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.service.UserService;
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
    private StudentHomeworkMapper studentHomeworkMapper;

    @Autowired
    private StudentHomeworkService studentHomeworkService;

    @Autowired
    private UserService userService;

    @Override
    public Teacher getTeacherById(int teacherID) throws ApplicationErrorException {

        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherID);
        if(teacher == null){
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);
        }

        return teacher;
    }

    @Override
    public ResTeacherClassList getAllTeacherClassInfoById(int teacherId) throws ApplicationErrorException {
        ResTeacherClassList resTeacherClassList = new ResTeacherClassList();
        List<ResClassDetail> resTeacherClasses = new ArrayList<>();
        resTeacherClassList.setTeacherClassList(resTeacherClasses);

        List<Clazz> classes = clazzMapper.selectByTeacherId(teacherId);
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
            ResTeacherList.ResTeacherInfo teacherInfo = new ResTeacherList.ResTeacherInfo();
            teacherInfoList.add(teacherInfo);

            teacherInfo.setId(teacher.getUserId());
            teacherInfo.setTeacherId(teacher.getId());
            teacherInfo.setTeacherName(teacher.getName());
            teacherInfo.setTeacherTitle(TitleEnum.fromInt(teacher.getTitle()).getZh());
            teacherInfo.setGender(teacher.getGender() == GenderEnum.MALE.getCode() ?
                    GenderEnum.MALE.getZh() : GenderEnum.FEMALE.getZh());
            teacherInfo.setTeacherContact(teacher.getEmail());
        }

        return teacherList;
    }

    @Override
    public void updateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {
        Teacher teacher = validateTeacher(reqUpdateTeacher);

        teacherMapper.updateByPrimaryKey(teacher);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteTeacherByTeacherId(int teacherId) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
        if(teacher == null)
            return;

        userService.deleteUserById(teacher.getUserId());
        teacherMapper.deleteByPrimaryKey(teacherId);
    }

    private Teacher validateTeacher(ReqUpdateTeacher reqUpdateTeacher) throws ApplicationErrorException {
        Teacher teacher = this.getTeacherById(reqUpdateTeacher.getTeacherId());
        //validate title enum
        TitleEnum title = TitleEnum.fromInt(reqUpdateTeacher.getTeacherTitleId());
        teacher.setTitle(title.getCode());
        teacher.setGender(reqUpdateTeacher.getGender() == GenderEnum.MALE.getCode() ?
                GenderEnum.MALE.getCode(): GenderEnum.FEMALE.getCode()
        );

        Utility.ValidateEmail(reqUpdateTeacher.getTeacherContact());
        teacher.setEmail(reqUpdateTeacher.getTeacherContact());
        return teacher;
    }
}
