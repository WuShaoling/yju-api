package com.guanshan.phoenix.webapp.dao.entity;

public class StudentCourse {

    private Integer courseId;
    private Integer studentId;
    private Integer id;
    private Integer termId;

    public Integer getCourse_id() {
        return courseId;
    }
    public void setCourse_id(Integer courseId) {
        this.courseId = courseId;
    }
    public Integer getStudent_id() {
        return studentId;
    }
    public void setStudent_id(Integer studentId) {
        this.studentId = studentId;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTerm_id() {
        return termId;
    }
    public void setTerm_id(Integer termId) {
        this.termId = termId;
    }
}