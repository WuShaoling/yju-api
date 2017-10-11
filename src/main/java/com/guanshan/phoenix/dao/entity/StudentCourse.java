package com.guanshan.phoenix.dao.entity;

public class StudentCourse {
    private Integer id;

    private Integer studentId;

    private Integer courseId;

    public StudentCourse(Integer id, Integer studentId, Integer courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public StudentCourse() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}