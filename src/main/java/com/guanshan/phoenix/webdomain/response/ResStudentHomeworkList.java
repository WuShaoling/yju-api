package com.guanshan.phoenix.webdomain.response;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */
public class ResStudentHomeworkList {

    private List<ResStudentHomework> homeworklist;

    public List<ResStudentHomework> getHomeworklist() {
        return homeworklist;
    }

    public void setHomeworklist(List<ResStudentHomework> homeworklist) {
        this.homeworklist = homeworklist;
    }

    public class ResStudentHomework {
        private ResHomework resHomework;
        private ResCloudware resCloudware;
        private String comment;
        private int score;
        private String submissionDate;
        private String lastEditDate;

        public ResHomework getResHomework() {
            return resHomework;
        }

        public void setResHomework(ResHomework resHomework) {
            this.resHomework = resHomework;
        }

        public ResCloudware getResCloudware() {
            return resCloudware;
        }

        public void setResCloudware(ResCloudware resCloudware) {
            this.resCloudware = resCloudware;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getSubmissionDate() {
            return submissionDate;
        }

        public void setSubmissionDate(String submissionDate) {
            this.submissionDate = submissionDate;
        }

        public String getLastEditDate() {
            return lastEditDate;
        }

        public void setLastEditDate(String lastEditDate) {
            this.lastEditDate = lastEditDate;
        }
    }

    public class ResHomework {
        private String name;
        private String description;
        private int publishDateMonth;
        private int publishDateDay;
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

        public int getPublishDateMonth() {
            return publishDateMonth;
        }

        public void setPublishDateMonth(int publishDateMonth) {
            this.publishDateMonth = publishDateMonth;
        }

        public int getPublishDateDay() {
            return publishDateDay;
        }

        public void setPublishDateDay(int publishDateDay) {
            this.publishDateDay = publishDateDay;
        }

        public String getDeadlineDate() {
            return deadlineDate;
        }

        public void setDeadlineDate(String deadlineDate) {
            this.deadlineDate = deadlineDate;
        }
    }

    public class ResCloudware {
        private String webSocket;
        private String serviceId;
        private String serviceName;
        private String instanceId;
        private String pulsarId;

        public String getWebSocket() {
            return webSocket;
        }

        public void setWebSocket(String webSocket) {
            this.webSocket = webSocket;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getPulsarId() {
            return pulsarId;
        }

        public void setPulsarId(String pulsarId) {
            this.pulsarId = pulsarId;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }
    }
}
