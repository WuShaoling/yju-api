package com.guanshan.phoenix.webapp.webdomain;

public class WebTeacher {

    private int teacherId;
    private String teacherName;
    private String teacherPosition;
    private String gender;
    private String teacherContact;

    public WebTeacher(int teacherId, String teacherName, String teacherPosition, String gender, String teacherContact) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherPosition = teacherPosition;
        this.gender = gender;
        this.teacherContact = teacherContact;
    }

    public WebTeacher() {

    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPosition() {
        return teacherPosition;
    }

    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTeacherContact() {
        return teacherContact;
    }

    public void setTeacherContact(String teacherContact) {
        this.teacherContact = teacherContact;
    }
}
