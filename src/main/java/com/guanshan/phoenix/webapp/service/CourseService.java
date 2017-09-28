package com.guanshan.phoenix.webapp.service;

import com.guanshan.phoenix.webapp.webdomain.WebCourse;
import com.guanshan.phoenix.webapp.webdomain.WebPeriodDetail;
import com.guanshan.phoenix.webapp.webdomain.WebStudentCourse;
import com.guanshan.phoenix.webapp.webdomain.WebTerm;

import java.util.List;

public interface CourseService {

    List<WebCourse> getAllCourseInfo();

    List<WebPeriodDetail> getAllPeriodDetailInfo(int courseId);

    List<WebTerm> getALlTermInfo();

    WebStudentCourse getStudentCourseInfo(int studentId);

}
