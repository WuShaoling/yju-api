package com.guanshan.phoenix.dao.entity;

public class StudentHomework {
    private Integer id;

    private Integer studentId;

    private Integer homeworkId;

    private Integer cloudwareId;

    private String comment;

    private Integer score;

    public StudentHomework(Integer id, Integer studentId, Integer homeworkId, Integer cloudwareId, String comment, Integer score) {
        this.id = id;
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.cloudwareId = cloudwareId;
        this.comment = comment;
        this.score = score;
    }

    public StudentHomework() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getCloudwareId() {
        return cloudwareId;
    }

    public void setCloudwareId(Integer cloudwareId) {
        this.cloudwareId = cloudwareId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}