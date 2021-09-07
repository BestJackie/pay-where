package com.example.paywhere.dao.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * FileName: TagVO
 * Author:   admin
 * Date:     2021-09-07 15:35
 * Description:
 * History:
 * since: 1.0.0
 */
@Data
public class TagVO {
    @NotEmpty
    private String name;
    private int type;
    private String owner;
}
