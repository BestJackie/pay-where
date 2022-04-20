package com.jc.paywhere.dao.entity;

public enum Perid {
    WEEK("每周"),
    MONTH("每月"),
    DAY("每天"),
    YEAR("每年"),
    ONES("一次性"),
    PAYROLL("工资单");
    private final String displayName;


    Perid(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
