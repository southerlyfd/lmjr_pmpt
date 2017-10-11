package com.pmpt.common;

public enum ActionLogCode {
    REGIST("0001", "regist"),
    LOGIN("0002", "login"),
    HOME("0003", "home"),
    ORG("0004","org");

    ActionLogCode() {
    }

    ActionLogCode(String code, String desc) {
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
