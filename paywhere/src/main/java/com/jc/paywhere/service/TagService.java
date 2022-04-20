package com.jc.paywhere.service;

import com.jc.paywhere.dao.entity.TagEntity;
import com.jc.paywhere.dao.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    void save(TagVO tagVO);

    Page<TagVO> listTag(Pageable pageRequest);

    TagEntity getByName(String tagName);

    void deleteById(Long id);
}
