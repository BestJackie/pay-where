package com.example.paywhere.service.impl;

import com.example.paywhere.commom.exception.MyException;
import com.example.paywhere.dao.entity.Role;
import com.example.paywhere.dao.entity.UserProfile;
import com.example.paywhere.dao.respository.UserProfileRespository;
import com.example.paywhere.dao.vo.UserVO;
import com.example.paywhere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl implements UserService {


    @Resource
    private UserProfileRespository userProfileRespository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserProfile getByUsername(String username) {
        return userProfileRespository.findByUsername(username);
    }

    @Override
    public void registUser(UserVO userVO) {
        if (Objects.nonNull(getByUsername(userVO.getUsername()))){
            throw new MyException("用户名已存在");
        }
        UserProfile user = new UserProfile();
        user.setPassword(passwordEncoder.encode(userVO.getPassword()));
        user.setEmail(userVO.getEmail());
        user.setUsername(userVO.getUsername());
        user.setRole(Role.USER);
        user.setModifyPwTime(LocalDateTime.now().minusDays(90));
        userProfileRespository.saveAndFlush(user);
    }
}
