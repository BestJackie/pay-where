package com.example.paywhere.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * FileName: UserProfile
 * Author:   admin
 * Date:     2021-07-29 10:03
 * Description:
 * History:
 * since: 1.0.0
 */

@Entity
@Table(name = "user")
@Data
public class UserProfile extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private Boolean isLock = false;
    private Boolean isExpiration = false;
    private LocalDateTime modifyPwTime;
    private String telephone;
    @Enumerated(EnumType.STRING)
    private Role role;

}
