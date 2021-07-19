package com.example.paywhere.web.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.stream.Collectors;

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
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<GrantedAuthority> authorities;

    public JwtUser() {
    }

    public JwtUser(SysUser user) {
        username = user.getUserName();
        password = user.getPassWord();
        isAccountNonExpired = user.getIsExpiration();
        isAccountNonLocked = user.getIsLock();
        LocalDate modifyDate = user.getModifyPwTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        isCredentialsNonExpired = LocalDate.now().toEpochDay() - modifyDate.toEpochDay() <= 90;
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            authorities = user.getRoles().stream().map(SysRole::getRoleName).map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toSet());
            if (!CollectionUtils.isEmpty(user.getPerms())) {
                Collection<GrantedAuthority> perms = user.getPerms().stream().map(SysPerm::getPermName).map(p -> new SimpleGrantedAuthority(p)).collect(Collectors.toSet());
                authorities.addAll(perms);
            }
        } else if (!CollectionUtils.isEmpty(user.getPerms())) {
            authorities = user.getPerms().stream().map(SysPerm::getPermName).map(p -> new SimpleGrantedAuthority(p)).collect(Collectors.toSet());
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
}