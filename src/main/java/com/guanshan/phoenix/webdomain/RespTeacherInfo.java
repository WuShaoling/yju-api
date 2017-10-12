package com.guanshan.phoenix.webdomain;

import java.util.List;

public class RespTeacherInfo {

    private List<RespTeacher> respTeacherList;

    public List<RespTeacher> getRespTeacherList() {
        return respTeacherList;
    }

    public void setRespTeacherList(List<RespTeacher> respTeacherList) {
        this.respTeacherList = respTeacherList;
    }

    public class RespTeacher {

        private int id;
        private String teacherId;
        private String teacherName;
        private String teacherTitle;
        private int gender;
        private String teacherContact;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
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
