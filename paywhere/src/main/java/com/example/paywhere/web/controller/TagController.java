package com.example.paywhere.web.controller;

import com.example.paywhere.commom.model.ServerResponse;
import com.example.paywhere.dao.vo.TagVO;
import com.example.paywhere.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private TagService tagService;

    @PostMapping
    public ServerResponse save(@RequestBody @Validated TagVO tagVO) {
        tagService.save(tagVO);
        return ServerResponse.success();
    }
}
