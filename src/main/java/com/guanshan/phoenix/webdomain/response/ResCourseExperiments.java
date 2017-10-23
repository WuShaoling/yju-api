package com.guanshan.phoenix.webdomain.response;

import java.util.List;

public class ResCourseExperiments {
    private int courseId;
    private String courseName;
    private List<ResExperimentInfo> experiments;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<ResExperimentInfo> getExperiments() {
        return experiments;
    }

    public void setExperiments(List<ResExperimentInfo> experiments) {
        this.experiments = experiments;
    }
}
