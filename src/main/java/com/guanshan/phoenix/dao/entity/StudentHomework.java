package com.guanshan.phoenix.dao.entity;

public class StudentHomework {
    private Integer id;

    private Integer studentId;

    private Integer homeworkId;

    private Integer periodId;

    private Integer score;

    public StudentHomework(Integer id, Integer studentId, Integer homeworkId, Integer periodId, Integer score) {
        this.id = id;
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.periodId = periodId;
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

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}