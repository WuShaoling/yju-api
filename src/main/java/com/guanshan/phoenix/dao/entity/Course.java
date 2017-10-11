package com.guanshan.phoenix.dao.entity;

import java.util.Date;

public class Course {
    private Integer id;

    private Integer semesterId;

    private Integer courseSid;

    private String name;

    private String image;

    private String description;

    private Date date;

    private String duration;

    private Integer studentNumber;

    public Course(Integer id, Integer semesterId, Integer courseSid, String name, String image, String description, Date date, String duration, Integer studentNumber) {
        this.id = id;
        this.semesterId = semesterId;
        this.courseSid = courseSid;
        this.name = name;
        this.image = image;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.studentNumber = studentNumber;
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

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getCourseSid() {
        return courseSid;
    }

    public void setCourseSid(Integer courseSid) {
        this.courseSid = courseSid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }
}