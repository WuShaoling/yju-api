package com.guanshan.phoenix.webapp.enums;

public enum SequenceEnum {
    ONE(1, "first term"),
    TWO(2, "second term"),
    THREE(3, "third term");

    private int code;
    private String string;

    SequenceEnum(int code, String string) {
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
