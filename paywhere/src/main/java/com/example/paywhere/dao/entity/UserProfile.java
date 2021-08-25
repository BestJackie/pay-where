package com.example.paywhere.dao.entity;

import lombok.Data;

import javax.persistence.*;
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
    private Boolean isLock;
    private Boolean isExpiration;
    private LocalDateTime modifyPwTime;
    private String telephone;
    @Enumerated(EnumType.STRING)
    private Role role;

}
