package com.guanshan.phoenix.webapp.enums;

import java.util.EnumSet;

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

    public static String getStringByCode(int code) {
        EnumSet<GenderEnum> genderEnums = EnumSet.allOf(GenderEnum.class);
        for (GenderEnum genderEnum : genderEnums) {
            if (genderEnum.getCode() == code) {
                return genderEnum.getString();
            }
        }
        return null;
    }
}
