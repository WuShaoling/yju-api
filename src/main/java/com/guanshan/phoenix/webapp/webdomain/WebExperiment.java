package com.guanshan.phoenix.webapp.webdomain;

public class WebExperiment {

    private int experimentId;
    private String experimentName;
    private String experimentDes;
    private String cloudwareId;

    public WebExperiment() {
    }

    public WebExperiment(int experimentId, String experimentName, String experimentDes, String cloudwareId) {

        this.experimentId = experimentId;
        this.experimentName = experimentName;
        this.experimentDes = experimentDes;
        this.cloudwareId = cloudwareId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
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

    public String getCloudwareId() {
        return cloudwareId;
    }

    public void setCloudwareId(String cloudwareId) {
        this.cloudwareId = cloudwareId;
    }
}
