package com.guanshan.phoenix.webapp.enums;

public enum RoleEnum {
    STUDENT(1, "student"),
    TEACHER(2, "teacher"),
    INSTRUCTOR(3, "instructor");

    private int code;
    private String string;

    RoleEnum(int code, String string) {
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
