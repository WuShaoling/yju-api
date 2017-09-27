package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Student {

    private Integer userId;
    private String name;
    private Date birth;
    private Integer gender;
    private String sno;
    private Integer id;

    public Integer getUser_id() {
        return userId;
    }
    public void setUser_id(Integer userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getSno() {
        return sno;
    }
    public void setSno(String sno) {
        this.sno = sno;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}