package com.guanshan.phoenix.webdomain.request;

import java.util.Date;

public class ReqExperiment {

    private int id;
    private int moduleId;
    private String experimentName;
    private int imageType;
    private String imageNameVersion;
    private String experimentContent;
    private Date experimentCreateDate;
    private Date experimentDueDate;
    private String experimentUrl;
    private String experimentDes;

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

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public String getImageNameVersion() {
        return imageNameVersion;
    }

    public void setImageNameVersion(String imageNameVersion) {
        this.imageNameVersion = imageNameVersion;
    }

    public String getExperimentContent() {
        return experimentContent;
    }

    public void setExperimentContent(String experimentContent) {
        this.experimentContent = experimentContent;
    }

    public Date getExperimentCreateDate() {
        return experimentCreateDate;
    }

    public void setExperimentCreateDate(Date experimentCreateDate) {
        this.experimentCreateDate = experimentCreateDate;
    }

    public Date getExperimentDueDate() {
        return experimentDueDate;
    }

    public void setExperimentDueDate(Date experimentDueDate) {
        this.experimentDueDate = experimentDueDate;
    }

    public String getExperimentUrl() {
        return experimentUrl;
    }

    public void setExperimentUrl(String experimentUrl) {
        this.experimentUrl = experimentUrl;
    }

    public String getExperimentDes() {
        return experimentDes;
    }

    public void setExperimentDes(String experimentDes) {
        this.experimentDes = experimentDes;
    }
}
