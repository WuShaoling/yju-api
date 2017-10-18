package com.guanshan.phoenix.enums;

public enum  ResourceTypeEnum {
    IMAGE(1),

    HOMEWORK(2);

    private int code;

    public int getCode() {
        return code;
    }

    ResourceTypeEnum(int index) {
        this.code = index;
    }
}
