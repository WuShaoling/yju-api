package com.guanshan.phoenix.webdomain.request;

public class ReqDeleteModule {
    private int moduleId;

    public ReqDeleteModule(int moduleId) {
        this.moduleId = moduleId;
    }

    public ReqDeleteModule() {
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
}
