package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.entity.StudentHomework;
import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.dao.mapper.ClazzMapper;
import com.guanshan.phoenix.dao.mapper.StudentHomeworkMapper;
import com.guanshan.phoenix.dao.mapper.TeacherMapper;
import com.guanshan.phoenix.enums.GenderEnum;
import com.guanshan.phoenix.enums.TitleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.StudentHomeworkService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.ReqHomeworkGrade;
import com.guanshan.phoenix.webdomain.ResClassDetail;
import com.guanshan.phoenix.webdomain.ResTeacherClassList;
import com.guanshan.phoenix.webdomain.ResTeacherList;
import com.sun.tools.javah.Gen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
