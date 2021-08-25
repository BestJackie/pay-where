package com.example.paywhere.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * FileName: ConsumeRecord
 * Author:   admin
 * Date:     2021-08-25 9:51
 * Description: 消费记录
 * History:
 * since: 1.0.0
 */

@Data
@Entity
public class ConsumeRecord extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "channl_code")
    private Channl channl;
    @ManyToMany
    @JoinTable(name="consume_tag",
            joinColumns=
            @JoinColumn(name="consume_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="tag_id", referencedColumnName="id")
    )
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    @CreatedBy
    private UserProfile consumer;

    private BigDecimal amount;

}
