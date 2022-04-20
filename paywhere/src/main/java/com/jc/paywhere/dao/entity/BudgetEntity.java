package com.jc.paywhere.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * FileName: Budget
 *
 * @author: admin
 * Date:     2021-12-17 10:09
 * Description: 预算
 * Since: 1.0.0
 */
@Entity
@Data
public class BudgetEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    @CreatedBy
    private UserEntity consumer;

    private String name;

    @Enumerated(EnumType.STRING)
    private Perid perid;

    /**
     * 周期起始日期
     */
    private LocalDate startDate;

    /**
     * 周期内预算
     */
    private BigDecimal bugget;
    /**
     * 是否滚存
     */
    private Boolean rollback;

}
