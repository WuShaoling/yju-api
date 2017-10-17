package com.guanshan.phoenix.dao.entity;

public class Course {
    private Integer id;

    private Integer teacherId;

    private String name;

    private String description;

    private String imageUrl;

    public Course(Integer id, Integer teacherId, String name, String description) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
        this.description = description;
    }

    public Course() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}