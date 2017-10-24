package com.guanshan.phoenix.dao.entity;

import java.util.Date;

public class Experiment {
    private Integer id;

    private Integer moduleId;

    private String name;

    private String description;

    private Integer cloudwareType;

    private Date publishDate;

    private Date deadlineDate;

    private String experimentContent;

    public Experiment(Integer id, Integer moduleId, String name, String description, Integer cloudwareType, Date publishDate, Date deadlineDate, String experimentContent) {
        this.id = id;
        this.moduleId = moduleId;
        this.name = name;
        this.description = description;
        this.cloudwareType = cloudwareType;
        this.publishDate = publishDate;
        this.deadlineDate = deadlineDate;
        this.experimentContent = experimentContent;
    }

    public Experiment(Integer id, Integer moduleId, String name, String description, Integer cloudwareType, Date publishDate, Date deadlineDate) {
        this(id, moduleId, name, description, cloudwareType, publishDate, deadlineDate, "");
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

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
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

    public Integer getCloudwareType() {
        return cloudwareType;
    }

    public void setCloudwareType(Integer cloudwareType) {
        this.cloudwareType = cloudwareType;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getExperimentContent() {
        return experimentContent;
    }

    public void setExperimentContent(String experimentContent) {
        this.experimentContent = experimentContent == null ? null : experimentContent.trim();
    }
}