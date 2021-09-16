package com.example.paywhere.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

/**
 * FileName: TagEntity
 * Author:   admin
 * Date:     2021-08-25 9:47
 * Description: 标签
 * History:
 * since: 1.0.0
 */
@Data
@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {
    private String name;
    @ManyToOne(optional = false)
    @CreatedBy
    private UserProfile owner;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @Enumerated(EnumType.STRING)
    private TagType type;


}
