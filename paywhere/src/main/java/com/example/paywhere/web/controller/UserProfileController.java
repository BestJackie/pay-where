package com.example.paywhere.web.controller;

import com.example.paywhere.commom.model.ServerResponse;
import com.example.paywhere.dao.vo.UserVO;
import com.example.paywhere.service.UserService;
import com.example.paywhere.validator.RegistorValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: UserProfileController
 * Author:   admin
 * Date:     2021-08-24 16:38
 * Description:
 * History:
 * since: 1.0.0
 */
@RestController
@RequestMapping("user")
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("regist")
    public ServerResponse regist(@Validated(RegistorValidator.class) @RequestBody UserVO userVO){
        userService.registUser(userVO);
        return ServerResponse.success();
    }
}
