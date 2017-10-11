package com.guanshan.phoenix.dao.entity;

public class Clazz {
    private Integer id;

    private Integer semesterId;

    private Integer courseId;

    private String name;

    private Integer studnetNum;

    public Clazz(Integer id, Integer semesterId, Integer courseId, String name, Integer studnetNum) {
        this.id = id;
        this.semesterId = semesterId;
        this.courseId = courseId;
        this.name = name;
        this.studnetNum = studnetNum;
    }

    public Clazz() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStudnetNum() {
        return studnetNum;
    }

    public void setStudnetNum(Integer studnetNum) {
        this.studnetNum = studnetNum;
    }
}