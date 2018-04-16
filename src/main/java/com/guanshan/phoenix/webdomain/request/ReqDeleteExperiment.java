package com.guanshan.phoenix.webdomain.request;

public class ReqDeleteExperiment {
    private int id;

    public ReqDeleteExperiment() {
    }

    public ReqDeleteExperiment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
