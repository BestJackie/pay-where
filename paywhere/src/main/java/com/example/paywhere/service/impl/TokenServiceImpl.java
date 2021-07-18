/**
 * FileName: TokenServiceImpl
 * Author:   haichaoyang3
 * Date:     2019/7/2 11:45
 * Description: tokenservice
 * History:
 */
package com.example.paywhere.service.impl;


import com.jc.demo.springbootdemo.commom.exception.MyException;
import com.jc.demo.springbootdemo.commom.model.Constant;
import com.jc.demo.springbootdemo.commom.model.ResponseCode;
import com.jc.demo.springbootdemo.commom.model.ServerResponse;
import com.jc.demo.springbootdemo.commom.util.JedisUtils;
import com.jc.demo.springbootdemo.commom.util.JwtTokenUtils;
import com.jc.demo.springbootdemo.commom.util.RandomUtil;
import com.jc.demo.springbootdemo.service.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:〈tokenservice〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
@Service
public class TokenServiceImpl implements TokenService {
    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtils jedisUtil;

    @Override
    public ServerResponse createToken() {
        String str = RandomUtil.UUID32();
        StringBuilder token = new StringBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(str);

        jedisUtil.set(token.toString(), token.toString(), Constant.Redis.EXPIRE_TIME_MINUTE);

        return ServerResponse.success(token.toString());
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {// header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isEmpty(token)) {// parameter中也不存在token
                throw new MyException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }

        if (!jedisUtil.exists(token)) {
            throw new MyException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        Boolean del = jedisUtil.del(token);
        if (!del) {
            throw new MyException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }


    @Override
    public String getToken(HttpServletRequest request, String headKey) {
        String tokenHead = request.getHeader(headKey);
        String token = tokenHead.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        return token;
    }
}