package com.guanshan.phoenix.webapp.dao.entity;

public class UserFileSystem {

    private String status;
    private String remark;
    private Integer user_id;
    private String personal_volume;
    private Integer id;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public String getPersonal_volume() {
        return personal_volume;
    }
    public void setPersonal_volume(String personal_volume) {
        this.personal_volume = personal_volume;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}