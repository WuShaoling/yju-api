package com.guanshan.phoenix.webapp.enums;

public enum GenderEnum {
    MALE(1, "male"),
    FEMALE(2, "female");

    private int code;
    private String string;

    GenderEnum(int code, String string) {
        this.code = code;
        this.string = string;
    }

    public int getCode() {
        return code;
    }

    public String getString() {
        return string;
    }
}
