package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.StudentClassMapper;
import com.guanshan.phoenix.dao.mapper.StudentMapper;
import com.guanshan.phoenix.service.*;
import com.guanshan.phoenix.webdomain.ResStudentClassList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private ClassService classService;

    @Autowired
    private TermService termService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Override
    public List<StudentClass> getAllStudentClassById(int studentID) {
        return studentClassMapper.selectByStudentID(studentID);
    }

    @Override
    public ResStudentClassList getAllStudentClassInfoById(int studentID) {
        ResStudentClassList resStudentClassInfoList = new ResStudentClassList();
        List<ResStudentClassList.ResStudentClass> resStudentClasses = new ArrayList<>();
        resStudentClassInfoList.setStudentClassList(resStudentClasses);

        List<StudentClass> studentClasses = this.getAllStudentClassById(studentID);
        for (StudentClass studentClass : studentClasses) {
            Clazz classInfo = classService.getClassById(studentClass.getClassId());
            Term termInfo = termService.getTermById(classInfo.getTermId());
            Course courseInfo = courseService.getCourseById(classInfo.getCourseId());
            Teacher teacherInfo = teacherService.getTeacherById(courseInfo.getTeacherId());

            ResStudentClassList.ResStudentClass resStudentClass = new ResStudentClassList.ResStudentClass();
            resStudentClass.setClassId(classInfo.getId());
            resStudentClass.setClassName(classInfo.getName());
            resStudentClass.setTerm(termInfo.getDescription());
            resStudentClass.setImage(courseInfo.getImageUrl());
            resStudentClass.setDuration(classInfo.getDuration());
            resStudentClass.setStudentNumber(classInfo.getStudentNum());
            resStudentClass.setCourseDescription(courseInfo.getDescription());
            resStudentClass.setCourseId(courseInfo.getId());
            resStudentClass.setCourseName(courseInfo.getName());
            resStudentClass.setTeacherContract(teacherInfo.getEmail());
            resStudentClass.setTeacherName(teacherInfo.getName());
            resStudentClass.setClassDate(classInfo.getDate().toString());
            resStudentClasses.add(resStudentClass);
        }

        return resStudentClassInfoList;
    }


}
