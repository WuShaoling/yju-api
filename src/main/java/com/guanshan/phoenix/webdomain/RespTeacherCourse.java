package com.guanshan.phoenix.webdomain;

import java.util.List;

public class RespTeacherCourse {

    private List<RespCourse> respCourseList;

    public List<RespCourse> getRespCourseList() {
        return respCourseList;
    }

    public void setRespCourseList(List<RespCourse> respCourseList) {
        this.respCourseList = respCourseList;
    }

    public class RespCourse {
        private int id;
        private String duration;
        private int studentNum;
        private String courseDes;
        private String courseId;
        private String courseName;
        private String className;
        private String semester;
        private String courseDate;
        private String TeacherContact;
        private String teacherName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getCourseDate() {
            return courseDate;
        }

        public void setCourseDate(String courseDate) {
            this.courseDate = courseDate;
        }

        public String getTeacherContact() {
            return TeacherContact;
        }

        public void setTeacherContact(String teacherContact) {
            TeacherContact = teacherContact;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }
    }
}
