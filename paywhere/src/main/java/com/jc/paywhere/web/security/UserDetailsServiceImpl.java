package com.jc.paywhere.web.security;


import com.jc.paywhere.dao.entity.UserEntity;
import com.jc.paywhere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * FileName: UserDetailServiceImpl
 * Author:   haichaoyang3
 * Date:     2019/9/10 11:12
 * Description:
 * History:
 * since: 1.0.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userService.getByUsername(s);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new JwtUser(user);
    }
}