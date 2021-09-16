package com.example.paywhere.service;

import com.example.paywhere.dao.entity.Tag;
import com.example.paywhere.dao.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    void save(TagVO tagVO);

    Page<TagVO> listTag(Pageable pageRequest);

    Tag getByName(String tagName);

    void deleteById(Long id);
}
