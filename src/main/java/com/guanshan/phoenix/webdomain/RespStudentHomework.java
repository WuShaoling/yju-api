package com.guanshan.phoenix.webdomain;

import java.util.List;

public class RespStudentHomework {

    List<RespPeriod> respPeriodList;

    public List<RespPeriod> getRespPeriodList() {
        return respPeriodList;
    }

    public void setRespPeriodList(List<RespPeriod> respPeriodList) {
        this.respPeriodList = respPeriodList;
    }

    public class RespPeriod {
        private String moduleName;
        private List<RespHomework> moduleContent;

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public List<RespHomework> getModuleContent() {
            return moduleContent;
        }

        public void setModuleContent(List<RespHomework> moduleContent) {
            this.moduleContent = moduleContent;
        }
    }

    public class RespHomework {
        private int id;
        private String experimentName;
        private String experimentDes;
        private String dueDate;
        private String date;

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
