package com.guanshan.phoenix.dao.entity;

public class Resource {
    private Integer id;

    private String name;

    private Integer type;

    private String url;

    public Resource(Integer id, String name, Integer type, String url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public Resource() {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}