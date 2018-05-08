package com.guanshan.phoenix.webdomain.request;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ReqCreateHomework {

    private int classId;
    private int moduleId;
    private String homeworkName;
    private String homeworkDes;
    private String homeworkCreateDate;
    private String homeworkDueDate;
    private int imageType;
    private String imageNameVersion;
    private String homeworkContent;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getHomeworkDes() {
        return homeworkDes;
    }

    public void setHomeworkDes(String homeworkDes) {
        this.homeworkDes = homeworkDes;
    }

    public String getHomeworkCreateDate() {
        return homeworkCreateDate;
    }

    public void setHomeworkCreateDate(String homeworkCreateDate) {
        this.homeworkCreateDate = homeworkCreateDate;
    }

    public String getHomeworkDueDate() {
        return homeworkDueDate;
    }

    public void setHomeworkDueDate(String homeworkDueDate) {
        this.homeworkDueDate = homeworkDueDate;
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

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }
}
