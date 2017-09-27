package com.guanshan.phoenix.webapp.dao.entity;

public class Clazz {

    private Integer courseId;
    private Integer termId;
    private Integer id;
    private String name;
    private String size;

    public Integer getCourse_id() {
        return courseId;
    }
    public void setCourse_id(Integer courseId) {
        this.courseId = courseId;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
}