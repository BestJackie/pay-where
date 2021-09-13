package com.example.paywhere.dao.entity;

public enum Channel {
    UNIPAY("银联"),
    ALIPAY("支付宝"),
    WEICHAT("微信");
    private String displayName;


   Channel(String displayName) {
       this.displayName=displayName;
   }

   public String getDisplayName(){
       return this.displayName;
   }
}
