package com.jc.paywhere.dao.vo;

import com.jc.paywhere.validator.RegistorValidator;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * FileName: UserVO
 * Author:   admin
 * Date:     2021-07-29 14:51
 * Description:
 * History:
 * since: 1.0.0
 */
@Data
public class UserVO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @Email(groups = RegistorValidator.class)
    private String email;
    private String telephone;
}
