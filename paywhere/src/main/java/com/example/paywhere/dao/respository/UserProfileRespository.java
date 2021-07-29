package com.example.paywhere.dao.respository;

import com.example.paywhere.dao.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRespository extends JpaRepository<UserProfile,Long> {



    UserProfile findByUsername(String username);

}
