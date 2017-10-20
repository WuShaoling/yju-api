package com.guanshan.phoenix.webdomain;

import com.guanshan.phoenix.dao.entity.Teacher;
import com.guanshan.phoenix.enums.GenderEnum;
import com.guanshan.phoenix.enums.TitleEnum;

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
        private String teacherNo;
        private String teacherName;
        private String teacherTitle;
        private String gender;
        private String teacherContact;

        public ResTeacherInfo(){}

        public ResTeacherInfo(Teacher teacher){
            this.setId(teacher.getUserId());
            this.setTeacherNo(teacher.getTno());
            this.setTeacherName(teacher.getName());
            TitleEnum title = TitleEnum.fromInt(teacher.getTitle());
            this.setTeacherTitle(title == null ? "" : title.getZh());
            this.setGender(teacher.getGender() == GenderEnum.MALE.getCode() ?
                    GenderEnum.MALE.getZh() : GenderEnum.FEMALE.getZh());
            this.setTeacherContact(teacher.getEmail());
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTeacherNo() {
            return teacherNo;
        }

        public void setTeacherNo(String teacherNo) {
            this.teacherNo = teacherNo;
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
