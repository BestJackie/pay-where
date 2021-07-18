/**
 * FileName: TestController
 * Author:   haichaoyang3
 * Date:     2019/5/30 15:17
 * Description: springbootdemo
 * History:
 */
package com.example.paywhere.web.controller;


import com.jc.demo.springbootdemo.commom.anno.AccessLimit;
import com.jc.demo.springbootdemo.commom.model.ServerResponse;
import com.jc.demo.springbootdemo.service.service.TokenService;
import com.jc.demo.springbootdemo.service.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:〈springbootdemo〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
@RestController
@RequestMapping("demo")
public class TestController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @GetMapping("token")
    public ServerResponse token() {
        return tokenService.createToken();
    }

    @AccessLimit(maxCount = 3, seconds = 60)
    @GetMapping("hello")
    public ServerResponse hello() {
        return ServerResponse.success("hello world");
    }

    @GetMapping("test")
    @PreAuthorize("hasRole('ADMIN')")
    public ServerResponse test() {
        Long result = 12222L;
        return ServerResponse.success("success", result);
    }

}