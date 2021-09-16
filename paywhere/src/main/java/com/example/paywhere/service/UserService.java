package com.example.paywhere.service;

import com.example.paywhere.dao.entity.UserProfile;
import com.example.paywhere.dao.vo.UserVO;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserProfile getByUsername(String username);

    UserProfile getUserFromToken(Authentication token);

    void registUser(UserVO userVO);
}
