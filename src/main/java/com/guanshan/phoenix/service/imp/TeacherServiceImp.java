package com.guanshan.phoenix.service.imp;

import com.guanshan.phoenix.dao.entity.Clazz;
import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.dao.entity.Semester;
import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.dao.mapper.*;
import com.guanshan.phoenix.service.TeacherService;
import com.guanshan.phoenix.webdomain.RespTeacherCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private SemesterMapper semesterMapper;


    @Override
    public RespTeacherCourse getTeacherCourse(int teacherId) {
        RespTeacherCourse respTeacherCourse = new RespTeacherCourse();

        List<RespTeacherCourse.RespCourse> respCourseList = new ArrayList<>();
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
        List<Integer> courseIds = teacherCourseMapper.selectCourseIdByTeacherId(teacherId);
        for (Integer courseId : courseIds) {
            Course course = courseMapper.selectByPrimaryKey(courseId);
            Semester semester = semesterMapper.selectByPrimaryKey(course.getSemesterId());
            List<Clazz> clazzList = clazzMapper.selectClazzByCourseId(courseId);
            // todo more than one class ??

            RespTeacherCourse.RespCourse respCourse = new RespTeacherCourse().new RespCourse();
            respCourse.setId(courseId);
            respCourse.setDuration(course.getDuration());
            respCourse.setStudentNum(course.getStudentNumber());
            respCourse.setCourseDes(course.getDescription());
            respCourse.setClassName(clazzList.get(0).getName());
            respCourse.setSemester(semester.getYear() + " " + semester.getSemester());
            respCourse.setCourseDate(course.getDate().toString());
            respCourse.setTeacherContact(teacher.getEmail());
            respCourse.setTeacherName(teacher.getName());

            respCourseList.add(respCourse);
        }
        respTeacherCourse.setRespCourseList(respCourseList);

        return respTeacherCourse;
    }
}
