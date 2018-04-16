package com.guanshan.phoenix.webdomain.response;

import java.util.List;

public class ResClassStudents {

    private List<ResClassStudent> studentList;

    public class ResClassStudent {
        private int id;
        private String studentNo;
        private String studentName;
        private int gender;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStudentNo() {
            return studentNo;
        }

        public void setStudentNo(String sno) {
            this.studentNo = sno;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }

    public List<ResClassStudent> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<ResClassStudent> studentList) {
        this.studentList = studentList;
    }
}
