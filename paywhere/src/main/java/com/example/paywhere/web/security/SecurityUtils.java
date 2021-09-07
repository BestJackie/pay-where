package com.example.paywhere.web.security;

import com.example.paywhere.dao.entity.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * FileName: SecurityUtils
 * Author:   admin
 * Date:     2021-09-07 15:46
 * Description:
 * History:
 * since: 1.0.0
 */

public class SecurityUtils {

    private SecurityUtils() {

    }

    public static UserProfile getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserProfile) authentication.getPrincipal();
    }
}
