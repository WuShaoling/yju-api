package com.guanshan.phoenix.webdomain;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ResExperiment {

    private String courseName;
    private String moduleName;
    private String experimentName;
    private String experimentContent;
    private int cloudwareType;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getExperimentContent() {
        return experimentContent;
    }

    public void setExperimentContent(String experimentContent) {
        this.experimentContent = experimentContent;
    }

    public int getCloudwareType() {
        return cloudwareType;
    }

    public void setCloudwareType(int cloudwareType) {
        this.cloudwareType = cloudwareType;
    }
}
