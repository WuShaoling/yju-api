package com.guanshan.phoenix.dao.entity;

public class Homework {
    private Integer id;

    private Integer periodId;

    private String name;

    private String description;

    public Homework(Integer id, Integer periodId, String name, String description) {
        this.id = id;
        this.periodId = periodId;
        this.name = name;
        this.description = description;
    }

    public Homework() {
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
}