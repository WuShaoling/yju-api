package com.guanshan.phoenix.dao.entity;

public class ModuleResource {
    private Integer id;

    private Integer moduleId;

    private Integer resourceId;

    public ModuleResource(Integer id, Integer moduleId, Integer resourceId) {
        this.id = id;
        this.moduleId = moduleId;
        this.resourceId = resourceId;
    }

    public ModuleResource() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}