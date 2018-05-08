package com.guanshan.phoenix.webdomain.response;

import com.guanshan.phoenix.Util.Utility;
import com.guanshan.phoenix.dao.entity.Homework;
import com.guanshan.phoenix.enums.ImageTypeEnum;

import java.util.List;

public class ResCourseHomeworks {
    private String courseName;

    private List<ModuleInfo> moduleList;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<ModuleInfo> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleInfo> moduleList) {
        this.moduleList = moduleList;
    }

    public static class ModuleInfo {

        private int moduleId;

        private String moduleName;

        private List<HomeworkInfo> moduleContent;

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public List<HomeworkInfo> getModuleContent() {
            return moduleContent;
        }

        public void setModuleContent(List<HomeworkInfo> moduleContent) {
            this.moduleContent = moduleContent;
        }
    }

    public static class HomeworkInfo{
        private int id;

        private String homeworkName;

        private String homeworkDes;

        private int imageTypeId;

        private String imageType;

        private String dueDate;

        private String publishDate;

        private boolean completed;

        public HomeworkInfo(){}

        public HomeworkInfo(Homework homework){
            this.setId(homework.getId());
            this.setHomeworkName(homework.getName());
            this.setHomeworkDes(homework.getDescription());
            this.setDueDate(Utility.formatDate(homework.getDeadlineDate()));
            this.setPublishDate(Utility.formatDate(homework.getPublishDate()));
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public int getImageTypeId() {
            return imageTypeId;
        }

        public void setImageTypeId(int imageTypeId) {
            this.imageTypeId = imageTypeId;

            ImageTypeEnum imageTypeEnum = ImageTypeEnum.fromInt(imageTypeId);
            imageType = imageTypeEnum.toString();
        }

        public String getImageType() {
            return imageType;
        }
    }
}
