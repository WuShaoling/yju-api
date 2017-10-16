package com.guanshan.phoenix.dao.entity;

public class CourseResource {
    private Integer id;

    private Integer courseId;

    private Integer resourceId;

    public CourseResource(Integer id, Integer courseId, Integer resourceId) {
        this.id = id;
        this.courseId = courseId;
        this.resourceId = resourceId;
    }

    public CourseResource() {
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

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}