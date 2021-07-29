/**
 * FileName: MyExceptionHandler
 * Author:   haichaoyang3
 * Date:     2019/6/20 16:51
 * Description: hand exception
 * History:
 */
package com.example.paywhere.commom.exception;

import com.example.paywhere.commom.exception.MyException;
import com.example.paywhere.commom.model.ServerResponse;
import com.example.paywhere.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:〈hand exception〉
 *
 * @author haichaoyang3
 * @create 2019/6/20
 * @since 1.0.0
 */
@RestControllerAdvice(basePackages = {"com.jc.demo.springbootdemo.web.controller"})
public class MyExceptionHandler {
    @Autowired
    private MailService mailService;

    @ExceptionHandler(Exception.class)
    public ServerResponse handleException(Exception e) {
        return ServerResponse.error("未知错误", e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    public ServerResponse handleException(MyException e) {
        Map<String, Object> date = new HashMap<>(2);
        date.put("date", LocalDate.now());
        List<ServerResponse> serverResponses = new ArrayList<>();
        serverResponses.add(ServerResponse.error(e.getMessage()));
        date.put("serverResponses", serverResponses);
        mailService.sendMail(date);
        return ServerResponse.error(e.getMessage());
    }


}