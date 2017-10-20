package com.guanshan.phoenix.webdomain;

public class ReqUpdateClass {
    private int classId;
    private String className;
    private int courseId;
    private String termYear;
    private int termSemester;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTermYear() {
        return termYear;
    }

    public void setTermYear(String termYear) {
        this.termYear = termYear;
    }

    public int getTermSemester() {
        return termSemester;
    }

    public void setTermSemester(int termSemester) {
        this.termSemester = termSemester;
    }
}
