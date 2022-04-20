package com.jc.paywhere.dao.entity;

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
public class PayEntity extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private Channel channel;

    @ManyToOne(optional = false)
    private TagEntity tagEntity;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    @CreatedBy
    private UserEntity consumer;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private BudgetEntity budgetEntity;

    private String mark;

}
