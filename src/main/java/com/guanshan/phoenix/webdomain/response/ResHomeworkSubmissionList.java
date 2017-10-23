package com.guanshan.phoenix.webdomain.response;

import java.util.List;

public class ResHomeworkSubmissionList {
    private List<ResHomeworkSubmissionDetail> homeworkSubmissionList;

    public List<ResHomeworkSubmissionDetail> getHomeworkSubmissionList() {
        return homeworkSubmissionList;
    }

    public void setHomeworkSubmissionList(List<ResHomeworkSubmissionDetail> homeworkSubmissionList) {
        this.homeworkSubmissionList = homeworkSubmissionList;
    }

    public static class ResHomeworkSubmissionDetail{
        private int studentHomeworkId;
        private int homeworkId;
        private int studentId;
        private String studentName;
        private boolean completed;
        private String dueDate;
        private String submissionDate;
        private String lastEditDate;

        public int getStudentHomeworkId() {
            return studentHomeworkId;
        }

        public void setStudentHomeworkId(int studentHomeworkId) {
            this.studentHomeworkId = studentHomeworkId;
        }

        public int getHomeworkId() {
            return homeworkId;
        }

        public void setHomeworkId(int homeworkId) {
            this.homeworkId = homeworkId;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
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
}
