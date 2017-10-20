package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.StudentClassMapper;
import com.guanshan.phoenix.dao.mapper.StudentMapper;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.service.*;
import com.guanshan.phoenix.webdomain.ReqUpdateStudent;
import com.guanshan.phoenix.webdomain.ResClassDetail;
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
    private StudentMapper studentMapper;


    @Override
    public List<StudentClass> getAllStudentClassById(int studentID) {
        return studentClassMapper.selectByStudentID(studentID);
    }

    @Override
    public ResStudentClassList getAllStudentClassInfoById(int studentID) throws ApplicationErrorException {
        ResStudentClassList resStudentClassInfoList = new ResStudentClassList();
        List<ResClassDetail> resStudentClasses = new ArrayList<>();
        resStudentClassInfoList.setStudentClassList(resStudentClasses);

        List<StudentClass> studentClasses = this.getAllStudentClassById(studentID);
        for (StudentClass studentClass : studentClasses) {
            ResClassDetail resClassDetailInfo = classService.getClassDetailInfo(studentClass.getClassId());
            resStudentClasses.add(resClassDetailInfo);
        }

        return resStudentClassInfoList;
    }

    @Override
    public int updateStudentInfo(ReqUpdateStudent reqUpdateStudent) throws ApplicationErrorException {
        Student student = new Student();
        student.setId(reqUpdateStudent.getStudentId());
        student.setName(reqUpdateStudent.getStudentName());
        student.setGender(reqUpdateStudent.getGender());
        studentMapper.updateByPrimaryKeySelective(student);

        return 0;
    }


}
