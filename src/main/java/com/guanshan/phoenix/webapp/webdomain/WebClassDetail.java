package com.guanshan.phoenix.webapp.webdomain;

public class WebClassDetail {

    private int studentId;
    private int gender;
    private String name;

    public WebClassDetail() {
    }

    public WebClassDetail(int studentId, int gender, String name) {
        this.studentId = studentId;
        this.gender = gender;
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
