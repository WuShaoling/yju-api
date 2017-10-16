package com.guanshan.phoenix.webdomain;

import java.util.List;

public class ResStudentClassList {
    private List<ResStudentClass> resStudentClassList;

    
    public class ResStudentClass {
        private int classId;
        private String className;
        private String term;
        private String image;
        private String date;
        private String duration;
        private int studentNumber;
        private int courseId;
        private String courseName;
        private String courseDescription;
        private String teacherName;

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(int studentNumber) {
            this.studentNumber = studentNumber;
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

        public String getCourseDescription() {
            return courseDescription;
        }

        public void setCourseDescription(String courseDescription) {
            this.courseDescription = courseDescription;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }
    }

    public List<ResStudentClass> getResStudentClassList() {
        return resStudentClassList;
    }

    public void setResStudentClassList(List<ResStudentClass> resStudentClassList) {
        this.resStudentClassList = resStudentClassList;
    }
}
