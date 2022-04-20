package com.jc.paywhere.service.impl;

import com.jc.paywhere.commom.exception.MyException;
import com.jc.paywhere.dao.entity.IdentifiableEntity;
import com.jc.paywhere.dao.entity.TagEntity;
import com.jc.paywhere.dao.respository.TagRepository;
import com.jc.paywhere.dao.vo.TagVO;
import com.jc.paywhere.service.TagService;
import com.jc.paywhere.web.security.SecurityUtils;
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
        TagEntity tagEntity = tagRepository.findByNameAndOwner(tagVO.getName(), SecurityUtils.getCurrentUser());
        if (Objects.nonNull(tagEntity))
            throw new MyException("标签已存在");
        tagEntity = new TagEntity();
        tagEntity.setName(tagVO.getName());
        tagEntity.setType(tagVO.getType());
        tagEntity.setVisibility(tagVO.getVisibility());
        tagRepository.save(tagEntity);
    }

    @Override
    public Page<TagVO> listTag(Pageable pageRequest) {
        return tagRepository.pageTag(SecurityUtils.getCurrentUser(), pageRequest);
    }

    @Override
    public TagEntity getByName(String tagName) {
        tagName = "%" + tagName + "%";
        return tagRepository.findByNameLikeAndAndOwner(tagName, SecurityUtils.getCurrentUser());
    }

    @Override
    public void deleteById(Long id) {
        IdentifiableEntity<Long> identifiable = new IdentifiableEntity<>();
        identifiable.setId(id);
        TagEntity tagEntity = tagRepository.findById(identifiable).orElseThrow(() -> new MyException("当前ID标签不存在"));
        if (!SecurityUtils.getCurrentUser().equals(tagEntity.getOwner())) {
            throw new MyException("只能更新自己的tag");
        }
        tagRepository.delete(tagEntity);
    }
}