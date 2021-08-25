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
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @CreatedBy
    private UserProfile owner;

    /*@ManyToMany(mappedBy="tags")
    public Set<ConsumeRecord> consumeRecords; */

}
