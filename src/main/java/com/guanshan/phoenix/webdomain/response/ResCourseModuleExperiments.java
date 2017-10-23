package com.guanshan.phoenix.webdomain.response;

import java.util.List;

public class ResCourseModuleExperiments {
    private String courseName;

    private List<ModuleInfo> moduleList;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<ModuleInfo> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleInfo> moduleList) {
        this.moduleList = moduleList;
    }

    public static class ModuleInfo {

        private int moduleId;

        private String moduleName;

        private List<ResExperimentInfo> moduleContent;

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public List<ResExperimentInfo> getModuleContent() {
            return moduleContent;
        }

        public void setModuleContent(List<ResExperimentInfo> moduleContent) {
            this.moduleContent = moduleContent;
        }
    }

}
