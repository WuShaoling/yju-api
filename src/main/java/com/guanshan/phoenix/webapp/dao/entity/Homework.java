package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Homework {

    private Date updateTime;
    private Integer periodId;
    private String description;
    private Date craeteTime;
    private Integer id;
    private String name;

    public Date getUpdate_time() {
        return updateTime;
    }
    public void setUpdate_time(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getPeriod_id() {
        return periodId;
    }
    public void setPeriod_id(Integer periodId) {
        this.periodId = periodId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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