package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class User {

    private String username;
    private Date updateTime;
    private String password;
    private Integer role;
    private Date craeteTime;
    private Integer id;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getUpdate_time() {
        return updateTime;
    }
    public void setUpdate_time(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getRole() {
        return role;
    }
    public void setRole(Integer role) {
        this.role = role;
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
}