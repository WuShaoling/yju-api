package com.guanshan.phoenix.webapp.webdomain;

public class WebCourse {

    private int courseId;
    private String courseName;
    private String courseDes;
    private String semester;
    private String teacherName;
    private String teacherEmail;

    public WebCourse(int courseId, String courseName, String courseDes, String semester, String teacherName, String teacherEmail) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDes = courseDes;
        this.semester = semester;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
    }

    public WebCourse() {

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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
}

