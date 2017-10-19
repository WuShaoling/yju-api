package com.guanshan.phoenix.webdomain;

import java.util.List;

public class ResTeacherList {

    private List<ResTeacherInfo> teacherInfoList;

    public List<ResTeacherInfo> getTeacherInfoList() {
        return teacherInfoList;
    }

    public void setTeacherInfoList(List<ResTeacherInfo> teacherInfoList) {
        this.teacherInfoList = teacherInfoList;
    }

    public static class ResTeacherInfo {
        private int id;
        private int teacherId;
        private String teacherName;
        private String teacherTitle;
        private String gender;
        private String teacherContact;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherTitle() {
            return teacherTitle;
        }

        public void setTeacherTitle(String teacherTitle) {
            this.teacherTitle = teacherTitle;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getTeacherContact() {
            return teacherContact;
        }

        public void setTeacherContact(String teacherContact) {
            this.teacherContact = teacherContact;
        }
    }
}
