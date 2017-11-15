package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.ResourceTypeEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.*;
import com.guanshan.phoenix.webdomain.request.*;
import com.guanshan.phoenix.webdomain.response.ResClassDetail;
import com.guanshan.phoenix.webdomain.response.ResClassInfos;
import com.guanshan.phoenix.webdomain.response.ResClassStudents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private StudentService studentService;

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseResourceMapper courseResourceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private HomeworkService homeworkService;

    @Override
    public Clazz getClassById(int classID) throws ApplicationErrorException {

        Clazz clazz = clazzMapper.selectByPrimaryKey(classID);
        if (clazz == null) {
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        return clazz;
    }

    @Override
    public int deleteClassStudent(ReqDeleteClassStudent reqDeleteClassStudent) throws ApplicationErrorException {
        if(!clazzMapper.isStudentInClass(reqDeleteClassStudent.getStudentId(), reqDeleteClassStudent.getClassId())){
            throw new ApplicationErrorException(ErrorCode.StudentNotInClass);
        }
        studentClassMapper.deleteByClassIdAndStudentId(reqDeleteClassStudent.getClassId(), reqDeleteClassStudent.getStudentId());
        return 0;
    }

    @Override
    public int addClassStudent(ReqAddClassStudent reqAddClassStudent) throws ApplicationErrorException {
        if(clazzMapper.selectByPrimaryKey(reqAddClassStudent.getClassId()) == null){
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        Student student = studentMapper.selectByStudentNo(reqAddClassStudent.getStudentNo());
        if(student != null){
            if(clazzMapper.isStudentInClass(student.getUserId(), reqAddClassStudent.getClassId())){
                throw new ApplicationErrorException(ErrorCode.StudentAlreadyInClass);
            }
            if(!reqAddClassStudent.isOverride() &&
                    !student.getName().equals(reqAddClassStudent.getStudentName())){
                throw new ApplicationErrorException(ErrorCode.DuplicateStudentNoFound, student.getSno(), student.getName());
            }
            student.setGender(reqAddClassStudent.getGender());
            student.setName(reqAddClassStudent.getStudentName());
            studentService.updateStudent(student);
        } else {
            student = new Student(
                    reqAddClassStudent.getStudentNo(), reqAddClassStudent.getStudentName(), reqAddClassStudent.getGender(), "");
            studentService.createStudent(student);
        }

        StudentClass studentClass = new StudentClass();
        studentClass.setClassId(reqAddClassStudent.getClassId());
        studentClass.setStudentId(student.getUserId());
        studentClassMapper.insert(studentClass);

        return 0;
    }

    @Override
    public ResClassStudents getAllClassStudentInfo(int classId) throws ApplicationErrorException {
        if (clazzMapper.selectByPrimaryKey(classId) == null) {
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }
        ResClassStudents resClassStudents = new ResClassStudents();

        List<ResClassStudents.ResClassStudent> resClassStudentList = new ArrayList<>();
        resClassStudents.setStudentList(resClassStudentList);
        for (Map studentInfo : studentClassMapper.getAllStudentByClassId(classId)){
            ResClassStudents.ResClassStudent resClassStudent = new ResClassStudents().new ResClassStudent();
            resClassStudent.setId((int)studentInfo.get("userId"));
            resClassStudent.setStudentNo((String) studentInfo.get("sno"));
            resClassStudent.setStudentName((String)studentInfo.get("name"));
            resClassStudent.setGender((int)studentInfo.get("gender"));

            resClassStudentList.add(resClassStudent);
        }

        resClassStudents.setStudentList(resClassStudentList);

        return resClassStudents;
    }

    @Override
    public int deleteClass(ReqDeleteClass reqDeleteClass) throws ApplicationErrorException {
        int classId = reqDeleteClass.getClassId();

        if (clazzMapper.selectByPrimaryKey(classId) == null) {
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

//        if (homeworkMapper.isClassUsedByHomework(classId)) {
//            throw new ApplicationErrorException(ErrorCode.ClassIsUsedByHomework);
//        }
//        if (studentClassMapper.isClassUsedByStudentClass(classId)) {
//            throw new ApplicationErrorException(ErrorCode.ClassIsUsedByStudentClass);
//        }

        for(Homework homework : homeworkMapper.selectByClassId(classId)){
            homeworkService.deleteHomework(new ReqDeleteHomework(homework.getId()));
        }

        studentClassMapper.deleteByClassId(classId);
        clazzMapper.deleteByPrimaryKey(classId);
        return 0;
    }

    @Override
    public int updateClassInfo(ReqUpdateClass reqUpdateClass) throws ApplicationErrorException {
        if(clazzMapper.selectByPrimaryKey(reqUpdateClass.getClassId()) == null){
            throw new ApplicationErrorException(ErrorCode.ClassNotExists);
        }

        Clazz clazz = new Clazz();
        clazz.setId(reqUpdateClass.getClassId());
        clazz.setName(reqUpdateClass.getClassName());
        clazz.setCourseId(reqUpdateClass.getCourseId());
        clazz.setTermId(reqUpdateClass.getTermId());
        clazz.setTeacherId(reqUpdateClass.getTeacherId());

        validateClass(clazz);

        clazzMapper.updateByPrimaryKeySelective(clazz);
        return 0;
    }

    @Override
    public int createClass(ReqAddClass reqAddClass) throws ApplicationErrorException {
        Clazz clazz = new Clazz();
        clazz.setName(reqAddClass.getClassName());
        clazz.setCourseId(reqAddClass.getCourseId());
        clazz.setTermId(reqAddClass.getTermId());
        clazz.setTeacherId(reqAddClass.getTeacherId());

        validateClass(clazz);

        clazzMapper.insertSelective(clazz);
        return 0;
    }

    @Override
    public ResClassInfos getAllClassInfo() {
        ResClassInfos resClassInfos = new ResClassInfos();
        List<ResClassInfos.ResClassInfo> resClassInfoList = new ArrayList<>();

        for (Map classInfo : clazzMapper.selectAllClassInfo()){
            ResClassInfos.ResClassInfo resClassInfo = new ResClassInfos.ResClassInfo();

            resClassInfo.setClassId((int)classInfo.get("classId"));
            resClassInfo.setClassName((String) classInfo.get("className"));
            resClassInfo.setCourseId((int)classInfo.get("courseId"));

            resClassInfo.setCourseName((String) classInfo.get("courseName"));
            resClassInfo.setCourseDes((String) classInfo.get("description"));

            resClassInfo.setTeacherId((int)classInfo.get("teacherId"));
            resClassInfo.setTeacherName((String) classInfo.get("teacherName"));
            resClassInfo.setTeacherContact((String) classInfo.get("email"));

            resClassInfo.setTerm(new Term(
                    (String) classInfo.get("year"),
                    (int) classInfo.get("semester")
            ).getDescription());

            resClassInfo.setCourseImage((String) classInfo.get("url"));

            resClassInfo.setDuration((String) classInfo.get("duration"));
            resClassInfo.setStudentNum(((Long) classInfo.get("studentNum")).intValue());
            resClassInfo.setCourseDate(Utility.formatDate((Date) classInfo.get("classDate")));

            resClassInfoList.add(resClassInfo);
        }

        resClassInfos.setClassInfoList(resClassInfoList);

        return resClassInfos;
    }

    private void validateClass(Clazz clazz) throws ApplicationErrorException {
        if(termMapper.selectByPrimaryKey(clazz.getTermId()) == null){
            throw new ApplicationErrorException(ErrorCode.TermNotExists);
        }

        if(courseMapper.selectByPrimaryKey(clazz.getCourseId()) == null){
            throw new ApplicationErrorException(ErrorCode.CourseNotExists);
        }

        if(teacherMapper.selectByUserId(clazz.getTeacherId()) == null){
            throw new ApplicationErrorException(ErrorCode.TeacherNotExists);
        }

    }
}
