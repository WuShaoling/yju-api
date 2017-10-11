package com.guanshan.phoenix.dao.entity;

import java.util.Date;

public class Student {
    private Integer id;

    private Integer userId;

    private String sno;

    private String name;

    private Integer gender;

    private Date brithday;

    public Student(Integer id, Integer userId, String sno, String name, Integer gender, Date brithday) {
        this.id = id;
        this.userId = userId;
        this.sno = sno;
        this.name = name;
        this.gender = gender;
        this.brithday = brithday;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }
}