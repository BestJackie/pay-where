package com.example.paywhere.dao.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * FileName: BaseVO
 *
 * @author: admin
 * Date:     2021-09-17 13:56
 * Description:
 * Since: 1.0.0
 */
@Getter
@Setter
public abstract class BaseVO {

    protected Long id;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    protected LocalDateTime createTime;
}
