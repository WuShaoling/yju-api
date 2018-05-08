package com.guanshan.phoenix.dao.entity;

import com.guanshan.phoenix.Util.Utility;

import java.text.ParseException;
import java.util.Date;

public class Homework {
    private Integer id;

    private Integer moduleId;

    private String name;

    private String description;

    private Integer imageId;

    private Integer imageType;

    private String imageNameVersion;

    private Date publishDate;

    private Date deadlineDate;

    private Integer classId;

    private String homeworkContent;

    public Homework(Integer id, Integer moduleId, String name, String description,
                    Date publishDate, Date deadlineDate, Integer classId,
                    Integer imageType, String imageName, String imageVersion, String homeworkContent) {
        this.id = id;
        this.moduleId = moduleId;
        this.name = name;
        this.description = description;
        this.imageType = imageType;
        this.imageNameVersion = imageName + ":" + imageVersion;
        this.publishDate = publishDate;
        this.deadlineDate = deadlineDate;
        this.classId = classId;
        this.homeworkContent = homeworkContent;
    }

    public Homework(Integer id, Integer moduleId, String name, String description,
                    Date publishDate, Date deadlineDate, Integer classId,
                    Integer imageType, String imageName, String imageVersion){
        this(id, moduleId, name, description, publishDate, deadlineDate, classId, imageType, imageName, imageVersion, "");
    }

    public Homework() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setPublishDate(String publishDate) {
        try {
            this.publishDate = Utility.parseShortDate(publishDate);

        } catch (ParseException e){}
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        try {
            this.deadlineDate = Utility.parseShortDate(deadlineDate);

        } catch (ParseException e){}
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public String getImageNameVersion() {
        return imageNameVersion;
    }

    public void setImageNameVersion(String imageNameVersion) {
        this.imageNameVersion = imageNameVersion;
    }
}