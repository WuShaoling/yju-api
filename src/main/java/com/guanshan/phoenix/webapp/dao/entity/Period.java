package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Period {

    private Date update_time;
    private Date create_time;
    private String name;
    private Integer course_id;
    private Integer term_id;
    private Integer id;

    public Date getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public Date getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getCourse_id() {
        return course_id;
    }
    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }
    public Integer getTerm_id() {
        return term_id;
    }
    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}