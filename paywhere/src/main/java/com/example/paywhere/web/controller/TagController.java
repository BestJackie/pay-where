package com.example.paywhere.web.controller;

import com.example.paywhere.commom.model.ServerResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: TagController
 * Author:   admin
 * Date:     2021-09-06 16:08
 * Description:
 * History:
 * since: 1.0.0
 */

@RestController
@RequestMapping("tag")
public class TagController {


    @GetMapping("user")
    @PreAuthorize("hasRole('USER')")
    public ServerResponse test() {
        return ServerResponse.success("行i行动发生了放假啊");
    }
}
