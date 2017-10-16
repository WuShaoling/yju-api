package com.guanshan.phoenix.dao.entity;

public class Navlist {
    private Integer id;

    private String name;

    private String url;

    private String uiClass;

    public Navlist(Integer id, String name, String url, String uiClass) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.uiClass = uiClass;
    }

    public Navlist() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUiClass() {
        return uiClass;
    }

    public void setUiClass(String uiClass) {
        this.uiClass = uiClass == null ? null : uiClass.trim();
    }
}