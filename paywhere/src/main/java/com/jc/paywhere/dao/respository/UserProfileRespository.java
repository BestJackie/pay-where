package com.jc.paywhere.dao.respository;

import com.jc.paywhere.dao.entity.IdentifiableEntity;
import com.jc.paywhere.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRespository extends JpaRepository<UserEntity, IdentifiableEntity<Long>> {
    UserEntity findByUsername(String username);
}
