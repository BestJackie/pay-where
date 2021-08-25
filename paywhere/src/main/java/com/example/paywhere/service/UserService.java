package com.example.paywhere.service;

import com.example.paywhere.dao.entity.UserProfile;
import com.example.paywhere.dao.vo.UserVO;

public interface UserService {

    UserProfile getByUsername(String username);

    void registUser(UserVO userVO);
}
