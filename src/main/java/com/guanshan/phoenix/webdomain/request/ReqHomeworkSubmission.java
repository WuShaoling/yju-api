package com.guanshan.phoenix.webdomain.request;

public class ReqHomeworkSubmission {
    private int homeworkId;
    private int studentId;
    private String homework_url;
    private String cloudware_url;
    private String cloudware_serviceId;
    private String cloudware_instanceId;
    private String cloudware_serviceName;
    private String cloudware_pulsarId;

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getHomework_url() {
        return homework_url;
    }

    public void setHomework_url(String homework_url) {
        this.homework_url = homework_url;
    }

    public String getCloudware_url() {
        return cloudware_url;
    }

    public void setCloudware_url(String cloudware_url) {
        this.cloudware_url = cloudware_url;
    }

    public String getCloudware_serviceId() {
        return cloudware_serviceId;
    }

    public void setCloudware_serviceId(String cloudware_serviceId) {
        this.cloudware_serviceId = cloudware_serviceId;
    }

    public String getCloudware_instanceId() {
        return cloudware_instanceId;
    }

    public void setCloudware_instanceId(String cloudware_instanceId) {
        this.cloudware_instanceId = cloudware_instanceId;
    }

    public String getCloudware_serviceName() {
        return cloudware_serviceName;
    }

    public void setCloudware_serviceName(String cloudware_serviceName) {
        this.cloudware_serviceName = cloudware_serviceName;
    }

    public String getCloudware_pulsarId() {
        return cloudware_pulsarId;
    }

    public void setCloudware_pulsarId(String cloudware_pulsarId) {
        this.cloudware_pulsarId = cloudware_pulsarId;
    }
}
