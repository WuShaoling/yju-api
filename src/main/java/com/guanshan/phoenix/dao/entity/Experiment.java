package com.guanshan.phoenix.dao.entity;

public class Experiment {
    private Integer id;

    private Integer moduleId;

    private String name;

    private String description;

    private Integer cloudwareType;

    public Experiment(Integer id, Integer moduleId, String name, String description, Integer cloudwareType) {
        this.id = id;
        this.moduleId = moduleId;
        this.name = name;
        this.description = description;
        this.cloudwareType = cloudwareType;
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
}