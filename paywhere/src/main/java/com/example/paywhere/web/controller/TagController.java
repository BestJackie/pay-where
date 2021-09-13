package com.example.paywhere.web.controller;

import com.example.paywhere.commom.model.ServerResponse;
import com.example.paywhere.dao.vo.TagVO;
import com.example.paywhere.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping
    public ServerResponse list(@PageableDefault PageRequest pageRequest) {
        Page<TagVO> ret = tagService.listTag(pageRequest);
        return ServerResponse.success(ret);
    }

    @GetMapping("/{tagName}")
    public ServerResponse getByNameLike(@PathVariable String tagName) {
        return ServerResponse.success(tagService.getByName(tagName));
    }


}
