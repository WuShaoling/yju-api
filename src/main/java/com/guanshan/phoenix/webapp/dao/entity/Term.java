package com.guanshan.phoenix.webapp.dao.entity;

public class Term {

    private String term_year;
    private Integer id;
    private Integer sequence;

    public String getTerm_year() {
        return term_year;
    }
    public void setTerm_year(String term_year) {
        this.term_year = term_year;
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