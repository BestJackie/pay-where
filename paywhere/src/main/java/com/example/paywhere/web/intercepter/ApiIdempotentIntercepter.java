/**
 * FileName: ApiIdempotentIntercepter
 * Author:   haichaoyang3
 * Date:     2019/6/14 14:39
 * Description: 接口幂等性拦截器
 * History:
 */
package com.example.paywhere.web.intercepter;

import com.jc.demo.springbootdemo.commom.anno.ApiIdempotent;
import com.jc.demo.springbootdemo.service.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Description:〈接口幂等性拦截器〉
 *
 * @author haichaoyang3
 * @create 2019/6/14
 * @since 1.0.0
 */
public class ApiIdempotentIntercepter implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        ApiIdempotent methodAnno = method.getAnnotation(ApiIdempotent.class);
        if (methodAnno != null) {
            check(request);
        }
        return true;
    }

    private void check(HttpServletRequest request) {
        tokenService.checkToken(request);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}