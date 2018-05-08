package com.guanshan.phoenix.webdomain.response;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.Experiment;
import com.guanshan.phoenix.enums.ImageTypeEnum;

public class ResExperimentInfo {
    private int id;

    private String courseName;

    private String moduleName;

    private String experimentName;

    private String experimentDes;

    private int imageTypeId;

    private String imageType;

    private String imageNameVersion;

    private String dueDate;

    private String publishDate;

    private String experimentUrl;

    private String experimentContent;

    public ResExperimentInfo() { }

    public ResExperimentInfo(Experiment experiment) {
        this.setId(experiment.getId());
        this.setExperimentName(experiment.getName());
        this.setExperimentDes(experiment.getDescription());
        this.setImageTypeId(experiment.getImageType());
        this.setDueDate(Utility.formatDate(experiment.getDeadlineDate()));
        this.setPublishDate(Utility.formatDate(experiment.getPublishDate()));
        this.setExperimentContent(experiment.getExperimentContent());
        this.setImageNameVersion(experiment.getImageNameVersion());
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

    public int getImageTypeId() {
        return imageTypeId;
    }

    public void setImageTypeId(int imageTypeId) {
        this.imageTypeId = imageTypeId;
        ImageTypeEnum imageTypeEnum = ImageTypeEnum.fromInt(imageTypeId);
        this.imageType = imageTypeEnum == null ? "" : imageTypeEnum.toString();
    }

    public String getImageNameVersion() {
        return imageNameVersion;
    }

    public void setImageNameVersion(String imageNameVersion) {
        this.imageNameVersion = imageNameVersion;
    }

    public String getImageType() {
        return imageType;
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
