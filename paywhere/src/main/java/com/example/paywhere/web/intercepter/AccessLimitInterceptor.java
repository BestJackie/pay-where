/**
 * FileName: AccessLimitInterceptor
 * Author:   haichaoyang3
 * Date:     2019/6/24 11:13
 * Description: 接口限流拦截器
 * History:
 */
package com.example.paywhere.web.intercepter;

import com.jc.demo.springbootdemo.commom.anno.AccessLimit;
import com.jc.demo.springbootdemo.commom.exception.MyException;
import com.jc.demo.springbootdemo.commom.model.Constant;
import com.jc.demo.springbootdemo.commom.model.ResponseCode;
import com.jc.demo.springbootdemo.commom.util.IpUtil;
import com.jc.demo.springbootdemo.commom.util.JedisUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Description:〈接口限流拦截器〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
public class AccessLimitInterceptor implements HandlerInterceptor {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AccessLimitInterceptor.class);
    @Autowired
    private JedisUtils jedisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
        if (Objects.nonNull(accessLimit)) {
            check(accessLimit, request);
        }
        return true;
    }

    private void check(AccessLimit accessLimit, HttpServletRequest request) {
        int maxCount = accessLimit.maxCount();
        int seconds = accessLimit.seconds();
        StringBuilder sb = new StringBuilder(Constant.Redis.ACCESS_LIMIT_PREFIX);
        sb.append(IpUtil.getIpAddress(request)).append(request.getRequestURI());
        String key = sb.toString();

        Boolean exists = jedisUtils.exists(key);
        if (!exists) {
            jedisUtils.set(key, String.valueOf(1), seconds);
        } else {
            Long ttl = jedisUtils.ttl(key);
            if (ttl <= 0) {
                jedisUtils.set(key, String.valueOf(1), seconds);
            } else {
                int count = Integer.parseInt(jedisUtils.get(key).toString());
                if (count < maxCount) {
                    count += 1;
                    jedisUtils.set(key, String.valueOf(count), ttl.intValue());
                } else {
                    throw new MyException(ResponseCode.ACCESS_LIMIT.getMsg());
                }
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}