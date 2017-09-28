package com.guanshan.phoenix.webapp.dao.entity;

public class StudentHomework {

    private Integer student_id;
    private String score_comments;
    private String answer_url;
    private String score;
    private Integer period_id;
    private String answer_content;
    private String cloudware_id;
    private Integer id;

    public Integer getStudent_id() {
        return student_id;
    }
    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }
    public String getScore_comments() {
        return score_comments;
    }
    public void setScore_comments(String score_comments) {
        this.score_comments = score_comments;
    }
    public String getAnswer_url() {
        return answer_url;
    }
    public void setAnswer_url(String answer_url) {
        this.answer_url = answer_url;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public Integer getPeriod_id() {
        return period_id;
    }
    public void setPeriod_id(Integer period_id) {
        this.period_id = period_id;
    }
    public String getAnswer_content() {
        return answer_content;
    }
    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }
    public String getCloudware_id() {
        return cloudware_id;
    }
    public void setCloudware_id(String cloudware_id) {
        this.cloudware_id = cloudware_id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}