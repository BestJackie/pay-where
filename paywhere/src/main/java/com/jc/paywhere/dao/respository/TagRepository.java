package com.jc.paywhere.dao.respository;

import com.jc.paywhere.dao.entity.IdentifiableEntity;
import com.jc.paywhere.dao.entity.TagEntity;
import com.jc.paywhere.dao.entity.UserEntity;
import com.jc.paywhere.dao.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<TagEntity, IdentifiableEntity<Long>> {

    TagEntity findByNameAndOwner(String name, UserEntity owner);

    @Query("select new com.example.paywhere.dao.vo.TagVO(t.id,t.name,t.type,t.owner.username,t.visibility,t.createTime) from TagEntity t where (t.visibility='PUBLIC')or (t.owner=?1 and t.visibility='PUBLIC')")
    Page<TagVO> pageTag(UserEntity owner, Pageable request);

    @Query("select t from TagEntity t where t.name like ?1 and (t.owner = ?2 or t.visibility='PUBLIC')")
    TagEntity findByNameLikeAndAndOwner(String name, UserEntity owner);

}
