package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.webdomain.*;

import java.util.List;

public interface CourseService {

    List<WebCourse> getAllCourseInfo();

    List<WebPeriodDetail> getAllPeriodDetailInfo(int courseId);

    List<WebTerm> getALlTermInfo();

    WebStudentCourse getStudentCourseInfo(int studentId);

    WebCourseDetail getCourseDetailInfo(int courseId);
}
