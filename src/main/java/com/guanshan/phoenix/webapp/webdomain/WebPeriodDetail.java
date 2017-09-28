package com.guanshan.phoenix.webapp.webdomain;

import java.util.List;

public class WebPeriodDetail {

    private int periodId;
    private String moduleName;
    private List<WebExperiment> moduleExperiment;

    public WebPeriodDetail() {
    }

    public WebPeriodDetail(int periodId, String moduleName, List<WebExperiment> moduleExperiment) {
        this.periodId = periodId;
        this.moduleName = moduleName;
        this.moduleExperiment = moduleExperiment;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<WebExperiment> getModuleExperiment() {
        return moduleExperiment;
    }

    public void setModuleExperiment(List<WebExperiment> moduleExperiment) {
        this.moduleExperiment = moduleExperiment;
    }
}


