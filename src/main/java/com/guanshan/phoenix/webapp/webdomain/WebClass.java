package com.guanshan.phoenix.webapp.webdomain;

public class WebClass {

    private int classId;
    private String className;
    private String term;
    private String teacherName;
    private String teacherMail;
    private int studentNum;

    public WebClass() {
    }

    public WebClass(int classId, String className, String term, String teacherName, String teacherMail, int studentNum) {
        this.classId = classId;
        this.className = className;
        this.term = term;
        this.teacherName = teacherName;
        this.teacherMail = teacherMail;
        this.studentNum = studentNum;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMail() {
        return teacherMail;
    }

    public void setTeacherMail(String teacherMail) {
        this.teacherMail = teacherMail;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }
}
