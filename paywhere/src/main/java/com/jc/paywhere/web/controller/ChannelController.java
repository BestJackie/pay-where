package com.jc.paywhere.web.controller;

import com.jc.paywhere.commom.model.ServerResponse;
import com.jc.paywhere.dao.entity.Channel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: ChannelController
 *
 * @author: admin
 * Date:     2021-09-15 17:23
 * Description:
 * Since: 1.0.0
 */

@RestController
@RequestMapping("channel")
public class ChannelController {

    @GetMapping
    public ServerResponse list() {
        return ServerResponse.success(Channel.values());
    }

}
