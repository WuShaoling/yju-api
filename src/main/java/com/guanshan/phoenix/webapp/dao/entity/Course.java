package com.guanshan.phoenix.webapp.dao.entity;

import java.sql.Date;

public class Course {

    private Integer teacher_id;
    private Date update_time;
    private String description;
    private int student_num;
    private Integer term_id;
    private Date date;
    private Date create_time;
    private String duration;
    private Integer id;
    private String name;

    public Integer getTeacher_id() {
        return teacher_id;
    }
    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }
    public Date getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getStudent_num() {
        return student_num;
    }
    public void setStudent_num(int student_num) {
        this.student_num = student_num;
    }
    public Integer getTerm_id() {
        return term_id;
    }
    public void setTerm_id(Integer term_id) {
        this.term_id = term_id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}