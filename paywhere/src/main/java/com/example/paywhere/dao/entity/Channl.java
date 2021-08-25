package com.example.paywhere.dao.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * FileName: Channl
 * Author:   admin
 * Date:     2021-08-25 9:58
 * Description: 消费渠道
 * History:
 * since: 1.0.0
 */
@Data
@Entity
public class Channl extends BaseEntity{

    private Integer code;
    private String name;
}
