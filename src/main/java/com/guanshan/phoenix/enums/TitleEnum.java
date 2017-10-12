package com.guanshan.phoenix.enums;

import java.util.EnumSet;

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

    public static String getStringByCode(int code) {
        EnumSet<TitleEnum> titleEnums = EnumSet.allOf(TitleEnum.class);
        for (TitleEnum titleEnum : titleEnums) {
            if (titleEnum.getCode() == code) {
                return titleEnum.getString();
            }
        }
        return null;
    }

    public static int getCodeByString(String string) {
        EnumSet<TitleEnum> titleEnums = EnumSet.allOf(TitleEnum.class);
        for (TitleEnum titleEnum : titleEnums) {
            if (titleEnum.getString() == string) {
                return titleEnum.getCode();
            }
        }
        return -1;
    }
}
