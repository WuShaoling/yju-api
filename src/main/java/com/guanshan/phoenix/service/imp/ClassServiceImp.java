package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.dao.entity.Term;
import com.guanshan.phoenix.dao.mapper.ClazzMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.service.TermService;
import com.guanshan.phoenix.webdomain.ResClassDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImp implements ClassService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private TermService termService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Override
    public Clazz getClassById(int classID) throws ApplicationErrorException {

        Clazz clazz = clazzMapper.selectByPrimaryKey(classID);
        if(clazz == null){
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        return clazz;
    }

    @Override
    public ResClassDetail getClassDetailInfo(int classID) throws ApplicationErrorException {
        Clazz classInfo = this.getClassById(classID);
        Term termInfo = termService.getTermById(classInfo.getTermId());
        Course courseInfo = courseService.getCourseById(classInfo.getCourseId());
        Teacher teacherInfo = teacherService.getTeacherById(courseInfo.getTeacherId());

        ResClassDetail resClassDetailInfo = new ResClassDetail();
        resClassDetailInfo.setClassId(classInfo.getId());
        resClassDetailInfo.setClassName(classInfo.getName());
        resClassDetailInfo.setTerm(termInfo.getDescription());
        resClassDetailInfo.setImage(courseInfo.getImageUrl());
        resClassDetailInfo.setDuration(classInfo.getDuration());
        resClassDetailInfo.setStudentNumber(classInfo.getStudentNum());
        resClassDetailInfo.setCourseDescription(courseInfo.getDescription());
        resClassDetailInfo.setCourseId(courseInfo.getId());
        resClassDetailInfo.setCourseName(courseInfo.getName());
        resClassDetailInfo.setTeacherContract(teacherInfo.getEmail());
        resClassDetailInfo.setTeacherName(teacherInfo.getName());
        resClassDetailInfo.setClassDate(classInfo.getDate().toString());
        return resClassDetailInfo;
    }

}
