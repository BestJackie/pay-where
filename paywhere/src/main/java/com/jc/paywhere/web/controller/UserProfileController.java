package com.jc.paywhere.web.controller;

import com.jc.paywhere.commom.model.ServerResponse;
import com.jc.paywhere.dao.vo.UserVO;
import com.jc.paywhere.service.UserService;
import com.jc.paywhere.validator.RegistorValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
