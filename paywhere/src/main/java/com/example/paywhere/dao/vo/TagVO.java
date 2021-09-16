package com.example.paywhere.dao.vo;

import com.example.paywhere.dao.entity.TagType;
import com.example.paywhere.dao.entity.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class TagVO {
    private Long id;
    @NotEmpty
    private String name;
    private TagType type;
    private String owner;
    private Visibility visibility;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    public TagVO(Long id, String name, TagType type, String owner, Visibility visibility, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.visibility = visibility;
        this.createTime = createTime;
    }
}
