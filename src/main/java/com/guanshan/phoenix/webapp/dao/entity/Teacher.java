package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Teacher {

    private Integer userId;
    private String name;
    private Integer title;
    private Integer gender;
    private String contact;
    private Date birth;
    private String tno;
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
    public Integer getTitle() {
        return title;
    }
    public void setTitle(Integer title) {
        this.title = title;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public String getTno() {
        return tno;
    }
    public void setTno(String tno) {
        this.tno = tno;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}