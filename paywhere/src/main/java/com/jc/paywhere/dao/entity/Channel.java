package com.jc.paywhere.dao.entity;

public enum Channel {
    UNIPAY("银联"),
    ALIPAY("支付宝"),
    CASH("现金"),
    CARD("刷卡"),
    WECHAT("微信");
    private final String displayName;


    Channel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
