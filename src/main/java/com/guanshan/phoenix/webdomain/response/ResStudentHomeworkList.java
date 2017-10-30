package com.guanshan.phoenix.webdomain.response;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */
public class ResStudentHomeworkList {

    private List<ResHomework> resHomeworkList;

    public class ResHomework {
        private int id;
        private String name;
        private String description;
        private String cloudwareType;
        private String publishDate;
        private String deadlineDate;
        private int classId;
        private String className;
        private Boolean complete;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getCloudwareType() {
            return cloudwareType;
        }

        public void setCloudwareType(String cloudwareType) {
            this.cloudwareType = cloudwareType;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getDeadlineDate() {
            return deadlineDate;
        }

        public void setDeadlineDate(String deadlineDate) {
            this.deadlineDate = deadlineDate;
        }

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Boolean getComplete() {
            return complete;
        }

        public void setComplete(Boolean complete) {
            this.complete = complete;
        }
    }

    public List<ResHomework> getResHomeworkList() {
        return resHomeworkList;
    }

    public void setResHomeworkList(List<ResHomework> resHomeworkList) {
        this.resHomeworkList = resHomeworkList;
    }
}
