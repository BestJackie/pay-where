package com.example.paywhere.dao.entity;

public enum Role {
    ADMIN("管理员"),
    USER("用户"),
    VIP("会员")
  ;

    Role(String displayName) {
        this.displayName = displayName;
    }

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }


}
