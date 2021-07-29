package com.example.paywhere.web.security;

import com.example.paywhere.commom.model.ServerResponse;
import com.example.paywhere.commom.util.JsonUtil;
import com.example.paywhere.commom.util.JwtTokenUtils;
import com.example.paywhere.dao.vo.UserVO;
import com.example.paywhere.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * FileName: JwtAuthenticationFilter
 * Author:   haichaoyang3
 * Date:     2019/9/10 11:29
 * Description: 用户账号的验证
 * History:
 * since: 1.0.0
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private UserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserVO user = new ObjectMapper().readValue(request.getInputStream(), UserVO.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
        Set<String> roles = new HashSet<>();
        Set<String> perms = new HashSet<>();
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        if (!CollectionUtils.isEmpty(authorities)) {
            for (GrantedAuthority authority : authorities) {
                String permOrRole = authority.getAuthority();
                if (permOrRole.startsWith("ROLE_"))
                    roles.add(permOrRole);
                else
                    perms.add(permOrRole);
            }
        }
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), roles, perms, false);
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=UTF-8");
        Map<Object, Object> data = new HashMap<>();
        data.put("tokenHead", JwtTokenUtils.TOKEN_PREFIX);
        data.put("token", token);
//        response.setHeader("Authorization", JwtTokenUtils.TOKEN_PREFIX + token);
        String tokensData = JsonUtil.objToStr(ServerResponse.success(data));
        response.getOutputStream().write(tokensData.getBytes());
//        userService.loginLog(request, jwtUser.getUsername());
//        response.sendRedirect("/index");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ServerResponse serverResponse = ServerResponse.error("认证失败");
        if (failed instanceof BadCredentialsException)
            serverResponse = ServerResponse.error("密码错误");
        if (failed instanceof UsernameNotFoundException)
            serverResponse = ServerResponse.error("用户名不存在");
        if (failed instanceof LockedException)
            serverResponse = ServerResponse.error("账户已锁定");
        if (failed instanceof CredentialsExpiredException)
            serverResponse = ServerResponse.error("密码已过期");
        String data = JsonUtil.objToStr(serverResponse);
        response.getOutputStream().write(data.getBytes());
    }


}
