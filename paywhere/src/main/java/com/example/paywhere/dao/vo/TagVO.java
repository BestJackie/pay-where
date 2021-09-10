package com.example.paywhere.dao.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    public TagVO(String name, int type, String owner, LocalDateTime createTime) {
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.createTime = createTime;
    }
}
