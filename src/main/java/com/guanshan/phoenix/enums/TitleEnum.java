package com.guanshan.phoenix.enums;

import com.guanshan.phoenix.error.ApplicationErrorException;
import com.guanshan.phoenix.error.ErrorCode;

public enum TitleEnum {
    PROFESSOR(1, "professor", "教授"),
    DEPUTY_PROFESSOR(2, "deputy professor", "副教师"),
    LECTURER(3, "lecturer", "讲师"),
    RESEARCHER(4, "RESEARCHER", "研究员"),
    ASSISTANT(5, "assistant", "助教");

    private int code;
    private String en;
    private String zh;

    public int getCode() {
        return code;
    }

    public String getEn() {
        return en;
    }

    public String getZh() {
        return zh;
    }

    TitleEnum(int index, String en, String zh) {
        this.code = index;
        this.en = en;
        this.zh = zh;
    }

    public static String getEnFromCode(int code) {
        for (TitleEnum titleEnum : TitleEnum.values()) {
            if (titleEnum.getCode() == code) {
                return titleEnum.getEn();
            }
        }
        return "null";
    }

    public static String getZhFromCode(int code) {
        for (TitleEnum titleEnum : TitleEnum.values()) {
            if (titleEnum.getCode() == code) {
                return titleEnum.getZh();
            }
        }
        return "null";
    }

    public static TitleEnum fromInt(int i) {
        if(i > TitleEnum.values().length){
            return null;
        }
        return TitleEnum.values()[i-1];
    }
}
