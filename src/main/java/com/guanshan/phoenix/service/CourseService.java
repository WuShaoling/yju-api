package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResCourseExperiments;
import com.guanshan.phoenix.webdomain.ResCourseHomeworks;
import com.guanshan.phoenix.webdomain.ResCourseList;

public interface CourseService {
    Course getCourseById(int courseID) throws ApplicationErrorException;

    ResCourseExperiments getCourseExperiments(int classID) throws ApplicationErrorException;

    ResCourseHomeworks getCourseHomeworks(int classID) throws ApplicationErrorException;

    ResCourseList getAllCourses() throws ApplicationErrorException;

    void createCourse(Course course) throws ApplicationErrorException;

    void updateCourse(Course course) throws ApplicationErrorException;

    void deleteCourse(int courseId);
}
