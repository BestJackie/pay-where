package com.example.paywhere.web.security;

import com.example.paywhere.commom.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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
        if (!StringUtils.hasText(tokenHeader) || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
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
        String roles = JwtTokenUtils.getUserRoles(token);
        if (StringUtils.hasText(userName)) {
            if (StringUtils.hasText(roles)) {
                return new UsernamePasswordAuthenticationToken(userName, null, AuthorityUtils.createAuthorityList("ROLE_" + roles));
            }
            return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
        }
        return null;
    }
}