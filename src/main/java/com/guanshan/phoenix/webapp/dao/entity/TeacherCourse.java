package com.guanshan.phoenix.webapp.dao.entity;

public class TeacherCourse {

    private Integer teacher_id;
    private Integer term_id;
    private Integer id;
    private Integer course_id;

    public Integer getTeacher_id() {
        return teacher_id;
    }
    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }
    public Integer getTerm_id() {
        return term_id;
    }
    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCourse_id() {
        return course_id;
    }
    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }
}