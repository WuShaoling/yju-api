package com.guanshan.phoenix.webdomain;

import io.swagger.models.auth.In;

import java.util.List;

public class ResClassInfos {

    private List<ResClassInfo> classInfoList;

    public static class ResClassInfo {
        private int classId;
        private int courseId;
        private String courseName;
        private String courseDes;
        private String teacherName;
        private String teacherContact;
        private String term;
        private String courseImage;
        private String duration;
        private Integer studentNum;
        private String courseDate;

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseDes() {
            return courseDes;
        }

        public void setCourseDes(String courseDes) {
            this.courseDes = courseDes;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherContact() {
            return teacherContact;
        }

        public void setTeacherContact(String teacherContact) {
            this.teacherContact = teacherContact;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getCourseImage() {
            return courseImage;
        }

        public void setCourseImage(String courseImage) {
            this.courseImage = courseImage;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public Integer getStudentNum() {
            return studentNum;
        }

        public void setStudentNum(Integer studentNum) {
            this.studentNum = studentNum;
        }

        public String getCourseDate() {
            return courseDate;
        }

        public void setCourseDate(String courseDate) {
            this.courseDate = courseDate;
        }
    }

    public List<ResClassInfo> getClassInfoList() {
        return classInfoList;
    }

    public void setClassInfoList(List<ResClassInfo> classInfoList) {
        this.classInfoList = classInfoList;
    }
}
