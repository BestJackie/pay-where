package com.example.paywhere.web.security;


import com.example.paywhere.dao.entity.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Objects;
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

    public JwtUser(UserProfile user) {
        username = user.getUsername();
        password = user.getPassword();
        isAccountNonExpired = user.getIsExpiration();
        isAccountNonLocked = user.getIsLock();
        LocalDate modifyDate = user.getModifyPwTime().toLocalDate();
        isCredentialsNonExpired = LocalDate.now().toEpochDay() - modifyDate.toEpochDay() <= 90;
        if (Objects.nonNull(user.getRole())){
            authorities.addAll(AuthorityUtils.createAuthorityList("ROLE_"+user.getRole().name()));
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