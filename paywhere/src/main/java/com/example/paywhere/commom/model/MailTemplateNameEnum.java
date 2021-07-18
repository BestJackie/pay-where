package com.example.paywhere.commom.model;

public enum MailTemplateNameEnum {

    DATA_MONITOR_REPORT("data_monitor_report.ftl", "邮件报警"),
    REMAIND_USER_CPW("change_pw.ftl", "温馨提示"),
    VERIFY_CODE("verify_code.ftl", "温馨提示");

    String code;

    String desc;

    private MailTemplateNameEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
