package com.jc.paywhere.web.security;

import com.jc.paywhere.dao.entity.UserEntity;
import com.jc.paywhere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * FileName: SecurityUtils
 * Author:   admin
 * Date:     2021-09-07 15:46
 * Description:
 * History:
 * since: 1.0.0
 */
@Component
public class SecurityUtils {
    @Autowired
    private UserService userService;

    private static SecurityUtils securityUtils;

    @PostConstruct
    public void init() {
        securityUtils = this;
        securityUtils.userService = this.userService;
    }

    public static UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return securityUtils.userService.getUserFromToken(authentication);
    }
}
