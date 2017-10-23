package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.EncryptionUtil;
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
import com.guanshan.phoenix.webdomain.request.ReqAddClassStudent;
import com.guanshan.phoenix.webdomain.response.ResBatchAddStudent;
import com.guanshan.phoenix.webdomain.request.ReqUpdateStudent;
import com.guanshan.phoenix.webdomain.response.ResClassDetail;
import com.guanshan.phoenix.webdomain.response.ResStudentClassList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Value("${default.password}")
    private String defaultPassword;

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

    @Autowired
    private ManagerService managerService;

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
        Student studentToUpdate = new Student(
                0, reqUpdateStudent.getId(), reqUpdateStudent.getStudentNo(), reqUpdateStudent.getStudentName(),
                reqUpdateStudent.getGender(), "");

        this.updateStudent(studentToUpdate);

        return 0;
    }

    @Override
    public ResBatchAddStudent batchStudentCreation(int classId, MultipartFile file) throws ApplicationErrorException {
        ResBatchAddStudent resBatchAddStudent = new ResBatchAddStudent();
        List<ResBatchAddStudent.FailureReason> failureReasonList = new ArrayList<>();

        int success = 0;
        int failure = 0;

        if (clazzMapper.selectByPrimaryKey(classId) == null) {
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        ExcelStudent excelStudent = ExcelUtil.studentExcelAnalysis(file);
        for (ExcelStudent.ExcelStudentElement excelStudentElement : excelStudent.getExcelStudentElementList()) {
            try {
                ReqAddClassStudent newClassStudent = new ReqAddClassStudent();
                newClassStudent.setClassId(classId);
                newClassStudent.setStudentNo(excelStudentElement.getStudentNum());
                newClassStudent.setStudentName(excelStudentElement.getStudentName());
                newClassStudent.setGender(excelStudentElement.getGender());
                newClassStudent.setOverride(true);

                classService.addClassStudent(newClassStudent);

                success += 1;
            } catch (ApplicationErrorException ex) {
                ResBatchAddStudent.FailureReason failureReason = new ResBatchAddStudent().new FailureReason();
                failureReason.setClassId(classId);
                failureReason.setStudentNum(excelStudentElement.getStudentNum());
                // todo
                failureReason.setReason(ex.getMessage());
                failureReasonList.add(failureReason);

                failure += 1;
            }
        }
        resBatchAddStudent.setSuccess(success);
        resBatchAddStudent.setFailure(failure);
        resBatchAddStudent.setFailureReasonList(failureReasonList);

        return resBatchAddStudent;
    }

    @Override
    public void createStudent(Student student) throws ApplicationErrorException {
        if(userMapper.selectByUserName(student.getSno()) != null) {
            throw new ApplicationErrorException(ErrorCode.StudentAlreadyExists, student.getSno());
        }

        User user = managerService.createUser(student.getSno(), RoleEnum.TEACHER);
        student.setUserId(user.getId());
        studentMapper.insert(student);
    }

    @Override
    public void updateStudent(Student studentToUpdate) throws ApplicationErrorException {
        Student originalStudent = studentMapper.selectByUserId(studentToUpdate.getUserId());
        if(originalStudent == null){
            throw new ApplicationErrorException(ErrorCode.StudentNotExists);
        }
        if( !originalStudent.getSno().equals(studentToUpdate.getSno())){
            //If student No is changed
            if(userMapper.selectByUserName(studentToUpdate.getSno()) != null){
                throw new ApplicationErrorException(ErrorCode.StudentAlreadyExists, studentToUpdate.getSno());
            }
        }

        studentMapper.updateByUserId(studentToUpdate);
        User user = userMapper.selectByPrimaryKey(studentToUpdate.getUserId());
        user.setUsername(studentToUpdate.getSno());
        userMapper.updateByPrimaryKey(user);
    }


}
