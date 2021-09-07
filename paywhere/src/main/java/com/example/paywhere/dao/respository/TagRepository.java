package com.example.paywhere.dao.respository;

import com.example.paywhere.dao.entity.IdentifiableEntity;
import com.example.paywhere.dao.entity.Tag;
import com.example.paywhere.dao.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, IdentifiableEntity<Long>> {

    Tag findByNameAndOwner(String name, UserProfile owner);

}
