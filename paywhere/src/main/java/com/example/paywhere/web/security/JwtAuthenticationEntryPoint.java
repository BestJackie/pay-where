package com.example.paywhere.web.security;

import com.jc.demo.springbootdemo.commom.model.ServerResponse;
import com.jc.demo.springbootdemo.commom.util.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FileName: JwtAuthenticationEntryPoint
 * Author:   haichaoyang3
 * Date:     2019/9/10 15:20
 * Description:
 * History:
 * since: 1.0.0
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        String reason = "统一处理：原因" + e.getMessage();
        httpServletResponse.getWriter().write(JsonUtil.objToStr(ServerResponse.error(reason)));
    }
}