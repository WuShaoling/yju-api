package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.*;

public interface CourseService {
    Course getCourseById(int courseID) throws ApplicationErrorException;

    ResCourseModuleExperiments getCourseModuleExperiments(int classId) throws ApplicationErrorException;

    ResCourseExperiments getCourseExperiments(int courseId) throws ApplicationErrorException;

    ResCourseHomeworks getCourseHomeworks(int classID) throws ApplicationErrorException;

    ResCourseList getAllCourses() throws ApplicationErrorException;

    void createCourse(ReqAddCourse reqAddCourse) throws ApplicationErrorException;

    void updateCourse(Course course) throws ApplicationErrorException;

    void deleteCourse(int courseId) throws ApplicationErrorException;
}
