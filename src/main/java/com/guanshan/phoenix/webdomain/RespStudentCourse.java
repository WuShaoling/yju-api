package com.guanshan.phoenix.webdomain;

import java.util.List;

public class RespStudentCourse {

    List<RespCourse> respCourseList;

    public RespStudentCourse() {
    }

    public RespStudentCourse(List<RespCourse> respCourseList) {

        this.respCourseList = respCourseList;
    }

    public List<RespCourse> getRespCourseList() {
        return respCourseList;
    }

    public void setRespCourseList(List<RespCourse> respCourseList) {
        this.respCourseList = respCourseList;
    }

    public class RespCourse {

        private int id;
        private String image;
        private String duration;
        private int studentNum;
        private String courseDes;
        private String courseId;
        private String courseName;
        private String semester;
        private String teacherContact;
        private String teacherName;

        public RespCourse() {
        }

        public RespCourse(int id, String image, String duration, int studentNum, String courseDes, String courseId, String courseName, String semester, String teacherContact, String teacherName) {
            this.id = id;
            this.image = image;
            this.duration = duration;
            this.studentNum = studentNum;
            this.courseDes = courseDes;
            this.courseId = courseId;
            this.courseName = courseName;
            this.semester = semester;
            this.teacherContact = teacherContact;
            this.teacherName = teacherName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getStudentNum() {
            return studentNum;
        }

        public void setStudentNum(int studentNum) {
            this.studentNum = studentNum;
        }

        public String getCourseDes() {
            return courseDes;
        }

        public void setCourseDes(String courseDes) {
            this.courseDes = courseDes;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getTeacherContact() {
            return teacherContact;
        }

        public void setTeacherContact(String teacherContact) {
            this.teacherContact = teacherContact;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }
    }
}
