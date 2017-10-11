package com.guanshan.phoenix.dao.entity;

import java.util.Date;

public class Experiment {
    private Integer id;

    private Integer periodId;

    private String name;

    private String description;

    private String cloudwareId;

    private Date publishDate;

    private Date expireDate;

    public Experiment(Integer id, Integer periodId, String name, String description, String cloudwareId, Date publishDate, Date expireDate) {
        this.id = id;
        this.periodId = periodId;
        this.name = name;
        this.description = description;
        this.cloudwareId = cloudwareId;
        this.publishDate = publishDate;
        this.expireDate = expireDate;
    }

    public Experiment() {
        super();
    }

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
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCloudwareId() {
        return cloudwareId;
    }

    public void setCloudwareId(String cloudwareId) {
        this.cloudwareId = cloudwareId == null ? null : cloudwareId.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}