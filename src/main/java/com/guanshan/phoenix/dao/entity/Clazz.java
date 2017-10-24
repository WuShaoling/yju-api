package com.guanshan.phoenix.dao.entity;

import java.util.Date;

public class Clazz {
    private Integer id;

    private Integer termId;

    private Integer courseId;

    private Date date;

    private String duration;

    private Integer studentNum;

    private String name;

    public Clazz(Integer id, Integer termId, Integer courseId, Date date, String duration, Integer studentNum, String name) {
        this.id = id;
        this.termId = termId;
        this.courseId = courseId;
        this.date = date;
        this.duration = duration;
        this.studentNum = studentNum;
        this.name = name;
    }

    public Clazz() {
        this.studentNum = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public Integer getStudentNum() {
        return studentNum == null ? 0 : studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}