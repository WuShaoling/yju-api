package com.guanshan.phoenix.webapp.enums;

public enum TitleEnum {
    PROFESSOR(1, "professor"),
    DEPUTY_PROFESSOR(2, "deputy professor"),
    LECTURER(3, "lecturer"),
    RESEARCHER(4, "RESEARCHER"),
    ASSISTANT(5, "assistant");

    private int code;
    private String string;

    TitleEnum(int code, String string) {
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
