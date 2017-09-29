package com.guanshan.phoenix.webapp.webdomain;

/**
 * Created by Administrator on 2017/9/29.
 */
public class WebPeriod {

    private int periodId;
    private String periodName;

    public WebPeriod() {
    }

    public WebPeriod(int periodId, String periodName) {

        this.periodId = periodId;
        this.periodName = periodName;
    }

    public int getPeriodId() {

        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }
}
