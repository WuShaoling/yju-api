package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.ClazzMapper;
import com.guanshan.phoenix.dao.mapper.StudentClassMapper;
import com.guanshan.phoenix.dao.mapper.StudentMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.service.TermService;
import com.guanshan.phoenix.webdomain.ReqDeleteClassStudent;
import com.guanshan.phoenix.webdomain.ResClassDetail;
import com.guanshan.phoenix.webdomain.ResClassStudents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Clazz getClassById(int classID) throws ApplicationErrorException {

        Clazz clazz = clazzMapper.selectByPrimaryKey(classID);
        if (clazz == null) {
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

    @Override
    public int deleteClassStudent(ReqDeleteClassStudent reqDeleteClassStudent) throws ApplicationErrorException {
        studentClassMapper.deleteByClassIdAndStudentId(reqDeleteClassStudent.getClassId(), reqDeleteClassStudent.getStudnetId());
        return 0;
    }

    @Override
    public int addClassStudent(int classId, int studentId) {
        StudentClass studentClass = new StudentClass();
        studentClass.setClassId(classId);
        studentClass.setStudentId(studentId);

        studentClassMapper.insert(studentClass);
        return 0;
    }

    @Override
    public ResClassStudents getAllClassStudentInfo(int classId) {
        ResClassStudents resClassStudents = new ResClassStudents();

        List<ResClassStudents.ResClassStudent> resClassStudentList = new ArrayList<>();
        List<StudentClass> studentClassList = studentClassMapper.selectByClassID(classId);
        for (StudentClass studentClass : studentClassList) {
            ResClassStudents.ResClassStudent resClassStudent = new ResClassStudents().new ResClassStudent();
            Student student = studentMapper.selectByPrimaryKey(studentClass.getStudentId());
            resClassStudent.setId(student.getId());
            resClassStudent.setSno(student.getSno());
            resClassStudent.setStudentName(student.getName());
            resClassStudent.setGender(student.getGender());

            resClassStudentList.add(resClassStudent);
        }
        resClassStudents.setStudentList(resClassStudentList);

        return resClassStudents;
    }

}
