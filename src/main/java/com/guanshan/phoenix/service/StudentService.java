package com.guanshan.phoenix.service;

import com.guanshan.phoenix.shared.util.codec.ApplicationErrorException;
import com.guanshan.phoenix.webdomain.ReqPasswdModify;
import com.guanshan.phoenix.webdomain.RespStudentCourse;
import com.guanshan.phoenix.webdomain.RespStudentCourseDetail;
import com.guanshan.phoenix.webdomain.RespStudentHomework;

public interface StudentService {

    void updatePassword(ReqPasswdModify reqPasswdModify) throws ApplicationErrorException;

    RespStudentCourse getStudentCourses(int studentId);

    RespStudentCourseDetail getCourseDetail(int courseId);

    RespStudentHomework getStudentHomework(int courseId);
}
