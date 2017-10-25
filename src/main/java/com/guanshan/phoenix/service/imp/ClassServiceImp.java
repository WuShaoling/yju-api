package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.cloudwareDomain.ReqCreateVolume;
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
        Teacher teacherInfo = teacherService.getTeacherByUserId(courseInfo.getTeacherId());

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
        resClassDetailInfo.setClassDate(Utility.formatDate(classInfo.getDate()));
        return resClassDetailInfo;
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
        List<StudentClass> studentClassList = studentClassMapper.selectByClassID(classId);
        for (StudentClass studentClass : studentClassList) {
            ResClassStudents.ResClassStudent resClassStudent = new ResClassStudents().new ResClassStudent();
            Student student = studentMapper.selectByUserId(studentClass.getStudentId());
            resClassStudent.setId(student.getUserId());
            resClassStudent.setStudentNo(student.getSno());
            resClassStudent.setStudentName(student.getName());
            resClassStudent.setGender(student.getGender());

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

        if (homeworkMapper.isClassUsedByHomework(classId)) {
            throw new ApplicationErrorException(ErrorCode.ClassIsUsedByHomework);
        }
        if (studentClassMapper.isClassUsedByStudentClass(classId)) {
            throw new ApplicationErrorException(ErrorCode.ClassIsUsedByStudentClass);
        }

        clazzMapper.deleteByPrimaryKey(classId);
        studentClassMapper.deleteByClassId(classId);
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

        validateClass(clazz);

        clazzMapper.insertSelective(clazz);
        return 0;
    }

    @Override
    public ResClassInfos getAllClassInfo() {
        ResClassInfos resClassInfos = new ResClassInfos();
        List<ResClassInfos.ResClassInfo> resClassInfoList = new ArrayList<>();

        List<Clazz> clazzList = clazzMapper.selectAll();
        for (Clazz clazz : clazzList) {
            ResClassInfos.ResClassInfo resClassInfo = new ResClassInfos.ResClassInfo();

            resClassInfo.setClassId(clazz.getId());
            resClassInfo.setClassName(clazz.getName());
            resClassInfo.setCourseId(clazz.getCourseId());

            Course course = courseMapper.selectByPrimaryKey(clazz.getCourseId());
            resClassInfo.setCourseName(course.getName());
            resClassInfo.setCourseDes(course.getDescription());

            Teacher teacher = teacherMapper.selectByUserId(course.getTeacherId());
            resClassInfo.setTeacherName(teacher.getName());
            resClassInfo.setTeacherContact(teacher.getEmail());

            Term term = termMapper.selectByPrimaryKey(clazz.getTermId());
            resClassInfo.setTerm(term.getDescription());

            CourseResource courseResource =
                    courseResourceMapper.selectByCourseIdAndType(clazz.getCourseId(), ResourceTypeEnum.IMAGE.getCode());
            Resource resource = resourceMapper.selectByPrimaryKey(courseResource.getResourceId());
            resClassInfo.setCourseImage(resource.getUrl());

            resClassInfo.setDuration(clazz.getDuration());
            resClassInfo.setStudentNum(clazz.getStudentNum());
            resClassInfo.setCourseDate(Utility.formatDate(clazz.getDate()));

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
    }
}
