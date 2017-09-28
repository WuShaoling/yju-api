package com.guanshan.phoenix.webapp.webdomain;

import java.util.List;

public class WebStudentCourse {

    private int studentId;
    private List<WebCourse> webCourseList;


    public WebStudentCourse() {
    }

    public WebStudentCourse(int studentId, List<WebCourse> webCourseList) {

        this.studentId = studentId;
        this.webCourseList = webCourseList;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<WebCourse> getWebCourseList() {
        return webCourseList;
    }

    public void setWebCourseList(List<WebCourse> webCourseList) {
        this.webCourseList = webCourseList;
    }
}
