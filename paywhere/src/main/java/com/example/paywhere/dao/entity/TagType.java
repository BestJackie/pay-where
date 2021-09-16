package com.example.paywhere.dao.entity;

import com.example.paywhere.commom.exception.MyException;

import java.util.Objects;

/**
 * FileName: TagType
 *
 * @author: admin
 * Date:     2021-09-16 14:36
 * Description:
 * Since: 1.0.0
 */
public enum TagType {
    OUT("支出"),
    IN("收入");

    TagType(String displayName) {
        this.displayName = displayName;
    }

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public static TagType getByName(String name) {
        for (TagType tagType : TagType.values()) {
            if (Objects.equals(tagType, TagType.valueOf(name))) {
                return tagType;
            }
        }
        throw new MyException("非法输入");
    }
}
