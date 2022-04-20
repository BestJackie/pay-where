package com.jc.paywhere.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
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
public class TagEntity extends BaseEntity {
    private String name;
    @ManyToOne(optional = false)
    @CreatedBy
    private UserEntity owner;

    /**
     * PUBLIC/PRIVATE
     */
    private String visibility;

    /**
     * IN/OUT
     */
    private String type;


}
