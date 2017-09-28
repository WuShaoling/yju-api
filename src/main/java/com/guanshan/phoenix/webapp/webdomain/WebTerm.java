package com.guanshan.phoenix.webapp.webdomain;

public class WebTerm {

    private int termId;
    private String semesterYear;
    private int semester;

    public WebTerm(int termId, String semesterYear, int semester) {
        this.termId = termId;
        this.semesterYear = semesterYear;
        this.semester = semester;
    }

    public WebTerm() {

    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
