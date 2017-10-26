package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.request.ReqAddCourse;
import com.guanshan.phoenix.webdomain.request.ReqDeleteCourse;
import com.guanshan.phoenix.webdomain.response.ResCourseHomeworks;
import com.guanshan.phoenix.webdomain.response.ResCourseList;
import com.guanshan.phoenix.webdomain.response.ResCourseModuleExperiments;
import com.guanshan.phoenix.webdomain.response.ResHotCourseList;

import java.util.List;

public interface CourseService {
    Course getCourseById(int courseID) throws ApplicationErrorException;

    ResCourseModuleExperiments getCourseModuleExperiments(int classId) throws ApplicationErrorException;

    ResCourseModuleExperiments getClassModuleExperiments(int classId) throws ApplicationErrorException;

    ResCourseHomeworks getCourseHomeworks(int classID, int studentId) throws ApplicationErrorException;

    ResCourseList getAllCourses() throws ApplicationErrorException;

    ResHotCourseList getHotCourses();

    void createCourse(ReqAddCourse reqAddCourse) throws ApplicationErrorException;

    void updateCourse(Course course) throws ApplicationErrorException;

    void deleteCourse(ReqDeleteCourse reqDeleteCourse) throws ApplicationErrorException;
}
