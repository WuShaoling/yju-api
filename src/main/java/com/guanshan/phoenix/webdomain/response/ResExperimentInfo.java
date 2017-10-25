package com.guanshan.phoenix.webdomain.response;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.Experiment;
import com.guanshan.phoenix.enums.CloudwareTypeEnum;

public class ResExperimentInfo {
    private int id;

    private String courseName;

    private String moduleName;

    private String experimentName;

    private String experimentDes;

    private String cloudwareType;

    private String dueDate;

    private String publishDate;

    private String experimentUrl;

    private String experimentContent;

    public ResExperimentInfo() { }

    public ResExperimentInfo(Experiment experiment) {
        this.setId(experiment.getId());
        this.setExperimentName(experiment.getName());
        this.setExperimentDes(experiment.getDescription());
        CloudwareTypeEnum cloudwareType = CloudwareTypeEnum.fromInt(experiment.getCloudwareType());
        this.setCloudwareType(cloudwareType == null ? "" : cloudwareType.toString());
        this.setDueDate(Utility.formatDate(experiment.getDeadlineDate()));
        this.setPublishDate(Utility.formatDate(experiment.getPublishDate()));
        this.setExperimentContent(experiment.getExperimentContent());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getExperimentDes() {
        return experimentDes;
    }

    public void setExperimentDes(String experimentDes) {
        this.experimentDes = experimentDes;
    }

    public String getCloudwareType() {
        return cloudwareType;
    }

    public void setCloudwareType(String cloudwareType) {
        this.cloudwareType = cloudwareType;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

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

    public String getExperimentUrl() {
        return experimentUrl;
    }

    public void setExperimentUrl(String experimentUrl) {
        this.experimentUrl = experimentUrl;
    }

    public String getExperimentContent() {
        return experimentContent;
    }

    public void setExperimentContent(String experimentContent) {
        this.experimentContent = experimentContent;
    }
}
