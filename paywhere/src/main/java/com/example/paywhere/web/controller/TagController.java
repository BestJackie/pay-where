package com.example.paywhere.web.controller;

import com.example.paywhere.commom.model.ServerResponse;
import com.example.paywhere.dao.vo.TagVO;
import com.example.paywhere.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ServerResponse list(@PageableDefault Pageable pageable) {
        Page<TagVO> ret = tagService.listTag(pageable);
        return ServerResponse.success(ret);
    }

    @GetMapping
    public ServerResponse getByNameLike(@RequestParam String tagName) {
        return ServerResponse.success(tagService.getByName(tagName));
    }

    @DeleteMapping
    public ServerResponse delete(@RequestParam Long id) {
        tagService.deleteById(id);
        return ServerResponse.success();
    }


}
