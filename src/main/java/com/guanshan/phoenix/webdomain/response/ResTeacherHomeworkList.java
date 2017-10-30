package com.guanshan.phoenix.webdomain.response;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */
public class ResTeacherHomeworkList {
    private List<ResClass> classList;

    public List<ResClass> getClassList() {
        return classList;
    }

    public void setClassList(List<ResClass> classList) {
        this.classList = classList;
    }

    public class ResClass {
        private String name;
        private List<ResHomework> homeworkList;

        public List<ResHomework> getHomeworkList() {
            return homeworkList;
        }

        public void setHomeworkList(List<ResHomework> homeworkList) {
            this.homeworkList = homeworkList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class ResHomework {
        private String name;
        private String description;
        private String cloudwareType;
        private String publishDate;
        private String deadlineDate;

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
    }
}
