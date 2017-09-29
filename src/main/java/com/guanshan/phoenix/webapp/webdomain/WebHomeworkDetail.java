package com.guanshan.phoenix.webapp.webdomain;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */
public class WebHomeworkDetail {

    private int homeworkId;
    private String homeworkName;
    List<WebStudentHomework> webStudentHomeworks;

    public WebHomeworkDetail(int homeworkId, String homeworkName, List<WebStudentHomework> webStudentHomeworks) {
        this.homeworkId = homeworkId;
        this.homeworkName = homeworkName;
        this.webStudentHomeworks = webStudentHomeworks;
    }

    public WebHomeworkDetail() {

    }

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

    public List<WebStudentHomework> getWebStudentHomeworks() {
        return webStudentHomeworks;
    }

    public void setWebStudentHomeworks(List<WebStudentHomework> webStudentHomeworks) {
        this.webStudentHomeworks = webStudentHomeworks;
    }
}
