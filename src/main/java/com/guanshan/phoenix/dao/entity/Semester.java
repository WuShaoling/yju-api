package com.guanshan.phoenix.dao.entity;

public class Semester {
    private Integer id;

    private String year;

    private Integer semester;

    public Semester(Integer id, String year, Integer semester) {
        this.id = id;
        this.year = year;
        this.semester = semester;
    }

    public Semester() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}