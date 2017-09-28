package com.guanshan.phoenix.webapp.dao.entity;

public class StudentCourse {

    private Integer course_id;
    private Integer student_id;
    private Integer id;
    private Integer term_id;

    public Integer getCourse_id() {
        return course_id;
    }
    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }
    public Integer getStudent_id() {
        return student_id;
    }
    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTerm_id() {
        return term_id;
    }
    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }
}