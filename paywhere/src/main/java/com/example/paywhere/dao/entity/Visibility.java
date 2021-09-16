package com.example.paywhere.dao.entity;

import com.example.paywhere.commom.exception.MyException;

import java.util.Objects;

public enum Visibility {
    PUBLIC("公有"),
    PRIVATE("私有");

    Visibility(String displayName) {
        this.displayName = displayName;
    }

    public static Visibility getByName(String name) {
        for (Visibility tagType : Visibility.values()) {
            if (Objects.equals(tagType, Visibility.valueOf(name))) {
                return tagType;
            }
        }
        throw new MyException("非法输入");
    }

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }
}
