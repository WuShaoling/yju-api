package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.ClazzMapper;
import com.guanshan.phoenix.dao.mapper.StudentClassMapper;
import com.guanshan.phoenix.dao.mapper.StudentMapper;
import com.guanshan.phoenix.dao.mapper.UserMapper;
import com.guanshan.phoenix.enums.RoleEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.excel.ExcelUtil;
import com.guanshan.phoenix.excel.domain.ExcelStudent;
import com.guanshan.phoenix.service.*;
import com.guanshan.phoenix.webdomain.ReqUpdateStudent;
import com.guanshan.phoenix.webdomain.ResClassDetail;
import com.guanshan.phoenix.webdomain.ResStudentClassList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<StudentClass> getAllStudentClassByUserId(int studentID) {
        return studentClassMapper.selectByStudentUserId(studentID);
    }

    @Override
    public ResStudentClassList getAllStudentClassInfoByUserId(int studentID) throws ApplicationErrorException {
        if(studentMapper.selectByUserId(studentID) == null){
            throw new ApplicationErrorException(ErrorCode.StudentNotExists);
        }
        ResStudentClassList resStudentClassInfoList = new ResStudentClassList();
        List<ResClassDetail> resStudentClasses = new ArrayList<>();
        resStudentClassInfoList.setStudentClassList(resStudentClasses);

        List<StudentClass> studentClasses = this.getAllStudentClassByUserId(studentID);
        for (StudentClass studentClass : studentClasses) {
            ResClassDetail resClassDetailInfo = classService.getClassDetailInfo(studentClass.getClassId());
            resStudentClasses.add(resClassDetailInfo);
        }

        return resStudentClassInfoList;
    }

    @Override
    public int updateStudentInfo(ReqUpdateStudent reqUpdateStudent) throws ApplicationErrorException {
        Student student = new Student();
        student.setUserId(reqUpdateStudent.getStudentId());
        student.setName(reqUpdateStudent.getStudentName());
        student.setGender(reqUpdateStudent.getGender());
        studentMapper.updateByUserId(student);

        return 0;
    }

    @Override
    public int batchStudentCreation(int classId, MultipartFile file) throws ApplicationErrorException {

        if (clazzMapper.selectByPrimaryKey(classId) == null) {
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        ExcelStudent excelStudent = ExcelUtil.studentExcelAnalysis(file);
        for (ExcelStudent.ExcelStudentElement excelStudentElement : excelStudent.getExcelStudentElementList()) {
            User user = new User();
            user.setUsername(excelStudentElement.getStudentNum());
            user.setRole(RoleEnum.STUDENT.getCode());
            userMapper.insertSelective(user);

            Student student = new Student();
            student.setUserId(user.getId());
            student.setSno(excelStudentElement.getStudentNum());
            student.setName(excelStudentElement.getStudentName());
            student.setGender(excelStudentElement.getGender());
            studentMapper.insertSelective(student);

            StudentClass studentClass = new StudentClass();
            studentClass.setStudentId(student.getId());
            studentClass.setClassId(classId);
            studentClassMapper.insertSelective(studentClass);
        }

        return 0;
    }


}
