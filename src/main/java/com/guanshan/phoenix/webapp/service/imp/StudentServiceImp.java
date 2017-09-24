package com.guanshan.phoenix.webapp.service.imp;

import com.guanshan.phoenix.webapp.dao.entity.Course;
import com.guanshan.phoenix.webapp.dao.entity.Student;
import com.guanshan.phoenix.webapp.dao.mapper.CourseMapper;
import com.guanshan.phoenix.webapp.dao.mapper.StudentCourseMapper;
import com.guanshan.phoenix.webapp.dao.mapper.StudentMapper;
import com.guanshan.phoenix.webapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Course> getAllCourses(Integer studentId) {
        List<Course> courses = new ArrayList<>();
        List<Integer> courseIds = studentCourseMapper.findAllCourseIdByStudentId(studentId);
        for (Integer id : courseIds) {
            courses.add(courseMapper.findOne(id));
        }
        return courses;
    }

    @Override
    public Student getStudentInfo(String username) {
        return studentMapper.findOneByUsername(username);
    }
}
