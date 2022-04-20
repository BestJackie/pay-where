package com.jc.paywhere.dao.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * FileName: ConsumeVO
 *
 * @author: admin
 * Date:     2021-09-17 13:55
 * Description:
 * Since: 1.0.0
 */
@Data
public class ConsumeVO extends BaseVO {

    private String channel;

    private TagVO tag;

    private BigDecimal amount;

    private String mark;
}
