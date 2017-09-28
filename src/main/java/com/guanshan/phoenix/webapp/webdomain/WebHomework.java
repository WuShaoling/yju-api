package com.guanshan.phoenix.webapp.webdomain;

public class WebHomework {

    private int homeworkId;
    private String name;
    private String description;

    public WebHomework() {
    }

    public WebHomework(int homeworkId, String name, String description) {

        this.homeworkId = homeworkId;
        this.name = name;
        this.description = description;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
