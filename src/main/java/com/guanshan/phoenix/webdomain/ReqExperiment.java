package com.guanshan.phoenix.webdomain;

public class ReqExperiment {

    private int id;
    private int moduleId;
    private String experimentName;
    private int cloudwareType;
    private String experimentContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public int getCloudwareType() {
        return cloudwareType;
    }

    public void setCloudwareType(int cloudwareType) {
        this.cloudwareType = cloudwareType;
    }

    public String getExperimentContent() {
        return experimentContent;
    }

    public void setExperimentContent(String experimentContent) {
        this.experimentContent = experimentContent;
    }
}
