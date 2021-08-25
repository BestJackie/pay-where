package com.example.paywhere.web.security;

import com.example.paywhere.commom.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileName: JwtAutjorizationFilter
 * Author:   haichaoyang3
 * Date:     2019/9/10 11:44
 * Description:
 * History:
 * since: 1.0.0
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (StringUtils.isEmpty(tokenHeader) || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiration(token)) {
            return null;
        }
        String userName = JwtTokenUtils.getUsername(token);
        List<String> authorities = new ArrayList<>();
        List<String> roles = JwtTokenUtils.getUserRoles(token);
//        List<String> perms = JwtTokenUtils.getUserPerms(token);
        if (StringUtils.hasText(userName)) {
            if (!CollectionUtils.isEmpty(roles)) {
                authorities.addAll(roles);
            }
           /* if (!CollectionUtils.isEmpty(perms)) {
                authorities.addAll(perms);
            }*/
            if (CollectionUtils.isEmpty(authorities)) {
                return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
            }
            return new UsernamePasswordAuthenticationToken(userName, null, roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toSet()));
        }
        return null;
    }
}