package com.guanshan.phoenix.webapp.webdomain;

/**
 * Created by Administrator on 2017/9/29.
 */
public class WebStudentHomework {

    private int studentId;
    private String studentName;
    private String cloudware_id;
    private String answer_url;
    private String answer_content;
    private String score_comments;
    private String score;
//    private boolean completed;


    public WebStudentHomework() {
    }

    public WebStudentHomework(int studentId, String studentName, String cloudware_id, String answer_url, String answer_content, String score_comments, String score) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.cloudware_id = cloudware_id;
        this.answer_url = answer_url;
        this.answer_content = answer_content;
        this.score_comments = score_comments;
        this.score = score;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCloudware_url() {
        return cloudware_id;
    }

    public void setCloudware_url(String cloudware_id) {
        this.cloudware_id = cloudware_id;
    }

    public String getAnswer_url() {
        return answer_url;
    }

    public void setAnswer_url(String answer_url) {
        this.answer_url = answer_url;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public String getScore_comments() {
        return score_comments;
    }

    public void setScore_comments(String score_comments) {
        this.score_comments = score_comments;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
