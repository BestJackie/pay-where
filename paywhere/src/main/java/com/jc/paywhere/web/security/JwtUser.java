package com.jc.paywhere.web.security;


import com.jc.paywhere.dao.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

/**
 * FileName: JwtUser
 * Author:   haichaoyang3
 * Date:     2019/9/10 11:13
 * Description:
 * History:
 * since: 1.0.0
 */
public class JwtUser implements UserDetails {

    private String username;
    private String password;
    private String role;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<GrantedAuthority> authorities;

    public JwtUser() {
    }

    public JwtUser(UserEntity user) {
        username = user.getUsername();
        password = user.getPassword();
        role = user.getRole().name();
        isAccountNonExpired = user.getIsExpiration();
        isAccountNonLocked = user.getIsLock();
        LocalDate modifyDate = user.getModifyPwTime().toLocalDate();
        isCredentialsNonExpired = LocalDate.now().toEpochDay() - modifyDate.toEpochDay() <= 90;
        if (Objects.nonNull(user.getRole())) {
            authorities = AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().name());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                " username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    public String getRole() {
        return role;
    }
}