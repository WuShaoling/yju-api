package com.guanshan.phoenix.dao.entity;

public class Module {
    private Integer id;

    private Integer courseId;

    private String name;

    public Module(Integer id, Integer courseId, String name) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
    }

    public Module() {
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
}