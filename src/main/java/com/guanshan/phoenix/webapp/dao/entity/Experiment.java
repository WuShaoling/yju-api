package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Experiment {

    private Date update_time;
    private Date create_time;
    private Integer period_id;
    private String description;
    private String cloudware_id;
    private Integer id;
    private String name;

    public Date getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public Date getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    public Integer getPeriod_id() {
        return period_id;
    }
    public void setPeriod_id(Integer period_id) {
        this.period_id = period_id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCloudware_id() {
        return cloudware_id;
    }
    public void setCloudware_id(String cloudware_id) {
        this.cloudware_id = cloudware_id;
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