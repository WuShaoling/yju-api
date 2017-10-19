package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.dao.mapper.ClazzMapper;
import com.guanshan.phoenix.dao.mapper.TeacherMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.ResClassDetail;
import com.guanshan.phoenix.webdomain.ResStudentClassList;
import com.guanshan.phoenix.webdomain.ResTeacherClassList;
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

    @Override
    public Teacher getTeacherById(int teacherID) throws ApplicationErrorException {

        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherID);
        if(teacher == null){
            throw new ApplicationErrorException(ErrorCode.TeacherIDNotExists);
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
}
