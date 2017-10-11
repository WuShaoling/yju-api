package com.guanshan.phoenix.webdomain;

import java.util.List;

public class RespStudentCourseDetail {

    List<RespPeriod> respPeriodList;

    public RespStudentCourseDetail() {
    }

    public RespStudentCourseDetail(List<RespPeriod> respPeriodList) {

        this.respPeriodList = respPeriodList;
    }

    public List<RespPeriod> getRespPeriodList() {

        return respPeriodList;
    }

    public void setRespPeriodList(List<RespPeriod> respPeriodList) {
        this.respPeriodList = respPeriodList;
    }

    public class RespPeriod {
        private String moduleName;
        List<RespExperiment> moduleContent;

        public RespPeriod() {
        }

        public RespPeriod(String moduleName, List<RespExperiment> moduleContent) {

            this.moduleName = moduleName;
            this.moduleContent = moduleContent;
        }

        public String getModuleName() {

            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public List<RespExperiment> getModuleContent() {
            return moduleContent;
        }

        public void setModuleContent(List<RespExperiment> moduleContent) {
            this.moduleContent = moduleContent;
        }
    }

    public class RespExperiment {
        private int id;
        private String experimentName;
        private String experimentDes;
        private String dueDate;         //销毁时间或过期时间
        private String date;            //发布时间

        public RespExperiment() {
        }

        public RespExperiment(int id, String experimentName, String experimentDes, String dueDate, String date) {

            this.id = id;
            this.experimentName = experimentName;
            this.experimentDes = experimentDes;
            this.dueDate = dueDate;
            this.date = date;
        }

        public int getId() {

            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getExperimentName() {
            return experimentName;
        }

        public void setExperimentName(String experimentName) {
            this.experimentName = experimentName;
        }

        public String getExperimentDes() {
            return experimentDes;
        }

        public void setExperimentDes(String experimentDes) {
            this.experimentDes = experimentDes;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
