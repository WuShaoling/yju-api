package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Course;
import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ResCourseExperiments;
import com.guanshan.phoenix.webdomain.ResCourseHomeworks;

public interface CourseService {
    Course getCourseById(int courseID) throws ApplicationErrorException;

    ResCourseExperiments getCourseExperiments(int classID) throws ApplicationErrorException;

    ResCourseHomeworks getCourseHomeworks(int classID) throws ApplicationErrorException;
}
