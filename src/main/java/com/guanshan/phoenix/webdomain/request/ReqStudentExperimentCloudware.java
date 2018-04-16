package com.guanshan.phoenix.webdomain.request;

public class ReqStudentExperimentCloudware {
    private int studentId;
    private int experimentId;
    private String cloudwareType;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public String getCloudwareType() {
        return cloudwareType;
    }

    public void setCloudwareType(String cloudwareType) {
        this.cloudwareType = cloudwareType;
    }
}
