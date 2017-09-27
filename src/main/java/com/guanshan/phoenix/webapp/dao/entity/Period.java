package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Period {

    private Date updateTime;
    private String name;
    private Integer courseId;
    private Integer termId;
    private Date craeteTime;
    private Integer id;

    public Date getUpdate_time() {
        return updateTime;
    }
    public void setUpdate_time(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
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
}