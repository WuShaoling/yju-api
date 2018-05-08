package com.guanshan.phoenix.webdomain.request;

/**
 * Created by Administrator on 2017/10/19.
 */
public class ReqUpdateHomework {
    private int homeworkId;
    private String homeworkName;
    private String homeworkDes;
    private String homeworkCreateDate;
    private String homeworkDueDate;
    private int imageType;
    private String imageNameVersion;
    private String homeworkContent;

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
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
