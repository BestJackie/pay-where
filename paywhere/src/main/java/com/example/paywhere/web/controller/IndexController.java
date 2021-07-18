package com.example.paywhere.web.controller;

import com.jc.demo.springbootdemo.commom.anno.AccessLimit;
import com.jc.demo.springbootdemo.commom.exception.MyException;
import com.jc.demo.springbootdemo.commom.model.ServerResponse;
import com.jc.demo.springbootdemo.commom.util.JedisUtils;
import com.jc.demo.springbootdemo.commom.util.RandomUtil;
import com.jc.demo.springbootdemo.dao.po.SysUserPo;
import com.jc.demo.springbootdemo.service.service.MailService;
import com.jc.demo.springbootdemo.service.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * FileName: LoginController
 * Author:   haichaoyang3
 * Date:     2020/5/9 15:17
 * Description:
 * History:
 * since: 1.0.0
 */
@Api(tags = "index")
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @AccessLimit(maxCount = 1, seconds = 300)
    @ApiOperation(value = "获取验证码")
    @GetMapping("verify_code")
    public ServerResponse getVerficationCode(@RequestParam @ApiParam(name = "邮箱") String email) {
        String code = RandomUtil.generateDigitalStr(6);
        jedisUtils.set(email, code, 300);
        mailService.sendMail(code, email);
        return ServerResponse.success();
    }

    @ApiOperation("注册")
    @PostMapping("regist")
    public ServerResponse regist(@Valid SysUserPo userPo) {
        String code = (String) jedisUtils.get(userPo.geteMail());
        if (StringUtils.isEmpty(code)) {
            throw new MyException("验证码失效");
        }
        if (Objects.equals(code, userPo.getVerifyCode())) {
            userService.registUser(userPo);
        } else {
            throw new MyException("验证码错误");
        }
        return ServerResponse.success();
    }

}
