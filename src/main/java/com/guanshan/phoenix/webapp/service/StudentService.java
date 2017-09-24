package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.dao.entity.Course;
import com.guanshan.phoenix.webapp.dao.entity.Student;

import java.util.List;

public interface StudentService {

    List<Course> getAllCourses(Integer studentId);

    Student getStudentInfo(String username);

}
