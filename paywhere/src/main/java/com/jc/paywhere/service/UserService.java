package com.jc.paywhere.service;

import com.jc.paywhere.dao.entity.UserEntity;
import com.jc.paywhere.dao.vo.UserVO;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserEntity getByUsername(String username);

    UserEntity getUserFromToken(Authentication token);

    void registUser(UserVO userVO);
}
