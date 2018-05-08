package com.guanshan.phoenix.enums;

public enum ImageTypeEnum {
    CLOUDWARE(1),
    NOTEBOOK(2),
    WEBIDE(3);

    private int code;

    public int getCode() {
        return code;
    }
    

    ImageTypeEnum(int index) {
        this.code = index;
    }

    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }

    public static ImageTypeEnum fromInt(int i){
        if(i > ImageTypeEnum.values().length || i <= 0){
            return null;
        }

        return ImageTypeEnum.values()[i-1];
    }
}
