package com.guanshan.phoenix.enums;

import java.util.EnumSet;

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

    public static String getStringByCode(int code) {
        EnumSet<RoleEnum> roleEnums = EnumSet.allOf(RoleEnum.class);
        for (RoleEnum roleEnum : roleEnums) {
            if (roleEnum.getCode() == code) {
                return roleEnum.getString();
            }
        }
        return null;
    }
}
