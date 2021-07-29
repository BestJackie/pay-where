package com.example.paywhere.service.impl;

import com.example.paywhere.dao.entity.UserProfile;
import com.example.paywhere.dao.respository.UserProfileRespository;
import com.example.paywhere.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FileName: UserServiceImpl
 * Author:   admin
 * Date:     2021-07-29 13:49
 * Description:
 * History:
 * since: 1.0.0
 */

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserProfileRespository userProfileRespository;

    @Override
    public UserProfile getByUsername(String username) {

        return userProfileRespository.findByUsername(username);

    }

}
