package com.example.paywhere.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;

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


    @Enumerated(EnumType.STRING)
    private Channel channel;

    @ManyToOne(optional = false)
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    @CreatedBy
    private UserProfile consumer;

    private BigDecimal amount;

    private String mark;

}
