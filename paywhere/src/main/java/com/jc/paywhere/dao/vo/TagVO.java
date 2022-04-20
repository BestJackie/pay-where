package com.jc.paywhere.dao.vo;

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
public class TagVO extends BaseVO {
    @NotEmpty
    private String name;
    private String type;
    private String owner;
    private String visibility;

    public TagVO(Long id, String name, String type, String owner, String visibility, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.visibility = visibility;
        this.createTime = createTime;
    }
}
