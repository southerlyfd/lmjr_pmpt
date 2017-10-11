package com.pmpt.entities.enums;

public enum GoodPicTypeEnum {
    MAIN_PIC(1, "main_pic"), CERTIFICATE(2, "certificate"), DESCRIBE(3, "describe");

    private Integer index;
    private String type;


    GoodPicTypeEnum() {
    }

    GoodPicTypeEnum(Integer index, String type) {
        this.index = index;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
