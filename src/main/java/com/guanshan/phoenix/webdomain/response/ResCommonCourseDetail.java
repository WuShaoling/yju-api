package com.guanshan.phoenix.webdomain.response;

public class ResCommonCourseDetail {
    private String teacherName;
    private int classNum;
    private int studentNum;

    public ResCommonCourseDetail(String teacherName, int classNum, int studentNum) {
        this.teacherName = teacherName;
        this.classNum = classNum;
        this.studentNum = studentNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }
}
