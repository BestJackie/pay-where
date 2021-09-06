package com.example.paywhere.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @CreatedBy
    private UserProfile owner;
    /**
     * 标签类型，0公有，1私有
     */
    private int type;


}
