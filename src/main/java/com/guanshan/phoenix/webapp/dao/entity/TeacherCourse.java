package com.guanshan.phoenix.webapp.dao.entity;

public class TeacherCourse {

    private Integer teacherId;
    private Integer termId;
    private Integer id;
    private Integer courseId;

    public Integer getTeacher_id() {
        return teacherId;
    }
    public void setTeacher_id(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public Integer getTerm_id() {
        return termId;
    }
    public void setTerm_id(Integer termId) {
        this.termId = termId;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCourse_id() {
        return courseId;
    }
    public void setCourse_id(Integer courseId) {
        this.courseId = courseId;
    }
}