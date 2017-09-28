package com.guanshan.phoenix.webapp.dao.entity;

public class Clazz {

    private Integer course_id;
    private Integer term_id;
    private Integer id;
    private String name;
    private Integer size;

    public Integer getCourse_id() {
        return course_id;
    }
    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
}