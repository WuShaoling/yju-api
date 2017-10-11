package com.guanshan.phoenix.dao.entity;

public class Semester {
    private Integer id;

    private String year;

    private String semester;

    public Semester(Integer id, String year, String semester) {
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester == null ? null : semester.trim();
    }
}