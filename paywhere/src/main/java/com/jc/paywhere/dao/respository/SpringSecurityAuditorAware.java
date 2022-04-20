package com.jc.paywhere.dao.respository;

import com.jc.paywhere.dao.entity.UserEntity;
import com.jc.paywhere.web.security.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * FileName: SpringSecurityAuditorAware
 * Author:   admin
 * Date:     2021-08-25 13:39
 * Description:
 * History:
 * since: 1.0.0
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<UserEntity> {

    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        return Optional.ofNullable(SecurityUtils.getCurrentUser());
    }
}
