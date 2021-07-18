package com.example.paywhere.web.controller;

import com.jc.demo.springbootdemo.commom.model.ServerResponse;
import com.jc.demo.springbootdemo.commom.util.JwtTokenUtils;
import com.jc.demo.springbootdemo.service.service.TokenService;
import com.jc.demo.springbootdemo.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: UserController
 * Author:   haichaoyang
 * Date:     2020/11/19 2:33 下午
 * Description:
 * History:
 * since: 1.0.0
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("refresh/token")
    public ServerResponse refreshToken(HttpServletRequest request) {
        String token = tokenService.getToken(request, JwtTokenUtils.TOKEN_HEADER);
        String refreshToken = JwtTokenUtils.refreshHeadToken(token);
        if (refreshToken == null) {
            return ServerResponse.error("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", JwtTokenUtils.TOKEN_PREFIX);
        return ServerResponse.success(tokenMap);
    }


    @GetMapping("info")
    public ServerResponse userInfo(HttpServletRequest request) {
        String token = tokenService.getToken(request, JwtTokenUtils.TOKEN_HEADER);
        String userName = JwtTokenUtils.getUsername(token);
        return ServerResponse.success(userService.getInfo(userName));
    }
}
