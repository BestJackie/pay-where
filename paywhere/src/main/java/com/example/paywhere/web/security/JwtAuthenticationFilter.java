package com.example.paywhere.web.security;

import com.example.paywhere.commom.model.ServerResponse;
import com.example.paywhere.commom.utils.JsonUtil;
import com.example.paywhere.commom.utils.JwtTokenUtils;
import com.example.paywhere.dao.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.paywhere.commom.utils.JwtTokenUtils.TOKEN_HEADER;
import static com.example.paywhere.commom.utils.JwtTokenUtils.TOKEN_PREFIX;

/**
 * FileName: JwtAuthenticationFilter
 * Author:   haichaoyang3
 * Date:     2019/9/10 11:29
 * Description: 用户账号的验证
 * History:
 * since: 1.0.0
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserVO user = new ObjectMapper().readValue(request.getInputStream(), UserVO.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        logger.debug("jwtUser:" + jwtUser.toString());
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), jwtUser.getRole(), false);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.addHeader(TOKEN_HEADER, TOKEN_PREFIX + token);
        String tokensData = JsonUtil.objToStr(ServerResponse.success());
        response.getOutputStream().write(tokensData.getBytes());
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
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.getOutputStream().write(data.getBytes());
    }


}
