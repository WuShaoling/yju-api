package com.guanshan.phoenix.dao.entity;

public class Period {
    private Integer id;

    private Integer courseId;

    private String name;

    private String description;

    public Period(Integer id, Integer courseId, String name, String description) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.description = description;
    }

    public Period() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}