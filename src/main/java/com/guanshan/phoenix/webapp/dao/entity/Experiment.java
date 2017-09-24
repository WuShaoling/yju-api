package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Experiment {

    private Integer id;
    private Integer periodId;
    private String name;
    private String description;
    private Date date;
    private String cloudwareUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCloudwareUrl() {
        return cloudwareUrl;
    }

    public void setCloudwareUrl(String cloudwareUrl) {
        this.cloudwareUrl = cloudwareUrl;
    }
}
