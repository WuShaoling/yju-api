package com.guanshan.phoenix.webdomain;

import java.util.List;

public class RespSemesterInfo {

    List<RespSemester> respSemesterList;

    public List<RespSemester> getRespSemesterList() {
        return respSemesterList;
    }

    public void setRespSemesterList(List<RespSemester> respSemesterList) {
        this.respSemesterList = respSemesterList;
    }

    public class RespSemester {
        private int id;
        private String semesterYear;
        private String semester;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSemesterYear() {
            return semesterYear;
        }

        public void setSemesterYear(String semesterYear) {
            this.semesterYear = semesterYear;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }
    }
}
