package com.guanshan.phoenix.webapp.dao.entity;

public class Term {

    private String termYear;
    private Integer id;
    private Integer sequence;

    public String getTerm_year() {
        return termYear;
    }
    public void setTerm_year(String termYear) {
        this.termYear = termYear;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}