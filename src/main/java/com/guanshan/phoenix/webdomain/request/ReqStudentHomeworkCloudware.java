package com.guanshan.phoenix.webdomain.request;

public class ReqStudentHomeworkCloudware {
    private int studentId;
    private int homeworkId;
    private String cloudwareType;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getCloudwareType() {
        return cloudwareType;
    }

    public void setCloudwareType(String cloudwareType) {
        this.cloudwareType = cloudwareType;
    }
}
