package com.guanshan.phoenix.dao.entity;

public class TeacherCourse {
    private Integer id;

    private Integer teacherId;

    private Integer courseId;

    public TeacherCourse(Integer id, Integer teacherId, Integer courseId) {
        this.id = id;
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

    public TeacherCourse() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}