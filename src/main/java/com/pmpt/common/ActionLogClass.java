package com.pmpt.common;

public enum ActionLogClass {
    INFO("0001", "info"),
    EXCEPTION("0002", "exception");

    ActionLogClass() {
    }

    ActionLogClass(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
