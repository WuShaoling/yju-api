package com.guanshan.phoenix.dao.entity;

public class Cloudware {
    private Integer id;

    private String webSocket;

    private String serviceId;

    private String instanceId;

    public Cloudware(Integer id, String webSocket, String serviceId, String instanceId) {
        this.id = id;
        this.webSocket = webSocket;
        this.serviceId = serviceId;
        this.instanceId = instanceId;
    }

    public Cloudware() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(String webSocket) {
        this.webSocket = webSocket == null ? null : webSocket.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }
}