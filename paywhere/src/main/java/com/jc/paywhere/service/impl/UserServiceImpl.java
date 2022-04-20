package com.jc.paywhere.service.impl;

import com.jc.paywhere.commom.exception.MyException;
import com.jc.paywhere.dao.entity.Role;
import com.jc.paywhere.dao.entity.UserEntity;
import com.jc.paywhere.dao.respository.UserProfileRespository;
import com.jc.paywhere.dao.vo.UserVO;
import com.jc.paywhere.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * FileName: UserServiceImpl
 * Author:   admin
 * Date:     2021-07-29 13:49
 * Description:
 * History:
 * since: 1.0.0
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Resource
    private UserProfileRespository userProfileRespository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserEntity getByUsername(String username) {
        return userProfileRespository.findByUsername(username);
    }

    @Cacheable(cacheNames = "users", key = "#token.name")
    @Override
    public UserEntity getUserFromToken(Authentication token) {
        UserEntity user = getByUsername(token.getName());
        log.debug("getUserFromToken: {}", user);
        return user;
    }

    @Override
    public void registUser(UserVO userVO) {
        if (Objects.nonNull(getByUsername(userVO.getUsername()))) {
            throw new MyException("用户名已存在");
        }
        UserEntity user = new UserEntity();
        user.setPassword(passwordEncoder.encode(userVO.getPassword()));
        user.setEmail(userVO.getEmail());
        user.setUsername(userVO.getUsername());
        user.setRole(Role.USER);
        user.setModifyPwTime(LocalDateTime.now());
        userProfileRespository.saveAndFlush(user);
    }
}
