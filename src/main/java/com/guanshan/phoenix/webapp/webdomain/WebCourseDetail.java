package com.guanshan.phoenix.webapp.webdomain;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */
public class WebCourseDetail {

    private int courseId;
    private String courseName;
    private String courseDes;
    private String date;
    private String duration;
    private int studentNum;
    private List<WebPeriod> webPeriodList;

    public WebCourseDetail() {
    }

    public WebCourseDetail(int courseId, String courseName, String courseDes, String date, String duration, int studentNum, List<WebPeriod> webPeriodList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDes = courseDes;
        this.date = date;
        this.duration = duration;
        this.studentNum = studentNum;
        this.webPeriodList = webPeriodList;
    }

    public int getCourseId() {

        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public List<WebPeriod> getWebPeriodList() {
        return webPeriodList;
    }

    public void setWebPeriodList(List<WebPeriod> webPeriodList) {
        this.webPeriodList = webPeriodList;
    }
}
