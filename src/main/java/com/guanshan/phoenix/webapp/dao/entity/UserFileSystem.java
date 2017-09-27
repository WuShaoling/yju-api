package com.guanshan.phoenix.webapp.dao.entity;

public class UserFileSystem {

    private String status;
    private String personalVolumn;
    private String remark;
    private Integer userId;
    private Integer id;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPersonal_volumn() {
        return personalVolumn;
    }
    public void setPersonal_volumn(String personalVolumn) {
        this.personalVolumn = personalVolumn;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getUser_id() {
        return userId;
    }
    public void setUser_id(Integer userId) {
        this.userId = userId;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}