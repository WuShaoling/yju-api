package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.*;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.enums.SemesterEnum;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;
import com.guanshan.phoenix.service.ClassService;
import com.guanshan.phoenix.service.CourseService;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.service.TermService;
import com.guanshan.phoenix.webdomain.*;
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
        resClassDetailInfo.setClassDate(classInfo.getDate().toString());
        return resClassDetailInfo;
    }

    @Override
    public int deleteClassStudent(ReqDeleteClassStudent reqDeleteClassStudent) throws ApplicationErrorException {
        studentClassMapper.deleteByClassIdAndStudentId(reqDeleteClassStudent.getClassId(), reqDeleteClassStudent.getStudnetId());
        return 0;
    }

    @Override
    public int addClassStudent(ReqAddClassStudent reqAddClassStudent) {
        StudentClass studentClass = new StudentClass();
        studentClass.setClassId(reqAddClassStudent.getClassId());
        studentClass.setStudentId(reqAddClassStudent.getStudentId());
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
            Student student = studentMapper.selectByUserId(studentClass.getStudentId());
            resClassStudent.setId(student.getUserId());
            resClassStudent.setSno(student.getSno());
            resClassStudent.setStudentName(student.getName());
            resClassStudent.setGender(student.getGender());

            resClassStudentList.add(resClassStudent);
        }
        resClassStudents.setStudentList(resClassStudentList);

        return resClassStudents;
    }

    @Override
    public int deleteClass(int classId) {
        //todo: delete homework, student_homework, cloudware
        clazzMapper.deleteByPrimaryKey(classId);
        studentClassMapper.deleteByClassId(classId);
        return 0;
    }

    @Override
    public int updateClassInfo(ReqUpdateClass reqUpdateClass) {
        Clazz clazz = new Clazz();
        clazz.setId(reqUpdateClass.getClassId());
        clazz.setName(reqUpdateClass.getClassName());
        clazz.setCourseId(reqUpdateClass.getCourseId());
        clazzMapper.updateByPrimaryKeySelective(clazz);

        Term term = new Term();
        term.setId(clazz.getTermId());
        term.setYear(reqUpdateClass.getTermYear());
        term.setSemester(reqUpdateClass.getTermSemester());
        termMapper.updateByPrimaryKeySelective(term);

        return 0;
    }

    @Override
    public int createClass(ReqAddClass reqAddClass) {
        Clazz clazz = new Clazz();
        clazz.setName(reqAddClass.getClassName());
        clazz.setCourseId(reqAddClass.getCourseId());
        clazz.setTermId(reqAddClass.getTermId());

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
            resClassInfo.setCourseId(clazz.getCourseId());

            Course course = courseMapper.selectByPrimaryKey(clazz.getCourseId());
            resClassInfo.setCourseName(course.getName());
            resClassInfo.setCourseDes(course.getDescription());

            Teacher teacher = teacherMapper.selectByUserId(course.getTeacherId());
            resClassInfo.setTeacherName(teacher.getName());
            resClassInfo.setTeacherContact(teacher.getEmail());

            Term term = termMapper.selectByPrimaryKey(clazz.getTermId());
            resClassInfo.setTerm(term.getDescription());

            CourseResource courseResource = courseResourceMapper.selectByCourseId(clazz.getCourseId());
            Resource resource = resourceMapper.selectByPrimaryKey(courseResource.getResourceId());
            resClassInfo.setCourseImage(resource.getUrl());

            resClassInfo.setDuration(clazz.getDuration());
            resClassInfo.setStudentNum(clazz.getStudentNum());
            resClassInfo.setCourseDate(clazz.getDate().toString());

            resClassInfoList.add(resClassInfo);
        }
        resClassInfos.setClassInfoList(resClassInfoList);

        return resClassInfos;
    }

}
