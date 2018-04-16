package com.guanshan.phoenix.dao.entity;

import com.guanshan.phoenix.enums.SemesterEnum;

public class Term {
    private Integer id;

    private String year;

    private Integer semester;

    public Term(Integer id, String year, Integer semester) {
        this.id = id;
        this.year = year;
        this.semester = semester;
    }

    public Term(String year, Integer semester){
        this(0, year, semester);
    }

    public Term() {
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

    public String getDescription(){
        SemesterEnum semesterEnum = SemesterEnum.fromInt(semester);
        return String.format("%s%s", year, semesterEnum == null ? "" : semesterEnum.getZh());
    }
}