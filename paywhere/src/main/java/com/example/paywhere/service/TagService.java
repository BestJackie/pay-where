package com.example.paywhere.service;

import com.example.paywhere.dao.entity.Tag;
import com.example.paywhere.dao.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TagService {

    void save(TagVO tagVO);

    Page<TagVO> listTag(PageRequest pageRequest);

    Tag getByName(String tagName);
}
