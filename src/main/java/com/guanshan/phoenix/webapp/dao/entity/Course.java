package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Course {

    private Integer teacherId;
    private Date updateTime;
    private String description;
    private String studentNum;
    private Integer termId;
    private Date date;
    private String duration;
    private Date craeteTime;
    private Integer id;
    private String name;

    public Integer getTeacher_id() {
        return teacherId;
    }
    public void setTeacher_id(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public Date getUpdate_time() {
        return updateTime;
    }
    public void setUpdate_time(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStudent_num() {
        return studentNum;
    }
    public void setStudent_num(String studentNum) {
        this.studentNum = studentNum;
    }
    public Integer getTerm_id() {
        return termId;
    }
    public void setTerm_id(Integer termId) {
        this.termId = termId;
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
        this.duration = duration;
    }
    public Date getCraete_time() {
        return craeteTime;
    }
    public void setCraete_time(Date craeteTime) {
        this.craeteTime = craeteTime;
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
}