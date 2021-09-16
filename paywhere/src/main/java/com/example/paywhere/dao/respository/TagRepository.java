package com.example.paywhere.dao.respository;

import com.example.paywhere.dao.entity.IdentifiableEntity;
import com.example.paywhere.dao.entity.Tag;
import com.example.paywhere.dao.entity.UserProfile;
import com.example.paywhere.dao.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, IdentifiableEntity<Long>> {

    Tag findByNameAndOwner(String name, UserProfile owner);

    @Query("select new com.example.paywhere.dao.vo.TagVO(t.id,t.name,t.type,t.owner.username,t.visibility,t.createTime) from Tag t where (t.visibility='PUBLIC')or (t.owner=?1 and t.visibility='PUBLIC')")
    Page<TagVO> pageTag(UserProfile owner, Pageable request);

    @Query("select t from Tag t where t.name like ?1 and (t.owner = ?2 or t.visibility='PUBLIC')")
    Tag findByNameLikeAndAndOwner(String name, UserProfile owner);

}
