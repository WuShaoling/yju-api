package com.guanshan.phoenix.webapp.dao.entity;

public class StudentHomework {

    private Integer studentId;
    private String scoreComments;
    private String answerUrl;
    private String socre;
    private Integer periodId;
    private String answerContent;
    private String cloudwareId;
    private Integer id;

    public Integer getStudent_id() {
        return studentId;
    }
    public void setStudent_id(Integer studentId) {
        this.studentId = studentId;
    }
    public String getScore_comments() {
        return scoreComments;
    }
    public void setScore_comments(String scoreComments) {
        this.scoreComments = scoreComments;
    }
    public String getAnswer_url() {
        return answerUrl;
    }
    public void setAnswer_url(String answerUrl) {
        this.answerUrl = answerUrl;
    }
    public String getSocre() {
        return socre;
    }
    public void setSocre(String socre) {
        this.socre = socre;
    }
    public Integer getPeriod_id() {
        return periodId;
    }
    public void setPeriod_id(Integer periodId) {
        this.periodId = periodId;
    }
    public String getAnswer_content() {
        return answerContent;
    }
    public void setAnswer_content(String answerContent) {
        this.answerContent = answerContent;
    }
    public String getCloudware_id() {
        return cloudwareId;
    }
    public void setCloudware_id(String cloudwareId) {
        this.cloudwareId = cloudwareId;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}