package com.example.paywhere.service.impl;

import com.example.paywhere.commom.exception.MyException;
import com.example.paywhere.dao.entity.IdentifiableEntity;
import com.example.paywhere.dao.entity.Tag;
import com.example.paywhere.dao.respository.TagRepository;
import com.example.paywhere.dao.vo.TagVO;
import com.example.paywhere.service.TagService;
import com.example.paywhere.web.security.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * FileName: TagServiceImpl
 *
 * @author admin
 * Date:     2021-09-07 15:38
 * Description:
 * History:
 * since: 1.0.0
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagRepository tagRepository;

    @Override
    public void save(TagVO tagVO) {
        Tag tag = tagRepository.findByNameAndOwner(tagVO.getName(), SecurityUtils.getCurrentUser());
        if (Objects.nonNull(tag))
            throw new MyException("标签已存在");
        tag = new Tag();
        tag.setName(tagVO.getName());
        tag.setType(tagVO.getType());
        tag.setVisibility(tagVO.getVisibility());
        tagRepository.save(tag);
    }

    @Override
    public Page<TagVO> listTag(Pageable pageRequest) {
        return tagRepository.pageTag(SecurityUtils.getCurrentUser(), pageRequest);
    }

    @Override
    public Tag getByName(String tagName) {
        return tagRepository.findByNameLikeAndAndOwner(tagName, SecurityUtils.getCurrentUser());
    }

    @Override
    public void deleteById(Long id) {
        IdentifiableEntity<Long> identifiable = new IdentifiableEntity<>();
        identifiable.setId(id);
        Tag tag = tagRepository.findById(identifiable).orElseThrow(() -> new MyException("当前ID标签不存在"));
        if (!SecurityUtils.getCurrentUser().equals(tag.getOwner())) {
            throw new MyException("只能更新自己的tag");
        }
        tagRepository.delete(tag);
    }
}