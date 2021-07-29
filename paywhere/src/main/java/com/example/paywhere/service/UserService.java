package com.example.paywhere.service;

import com.example.paywhere.dao.entity.UserProfile;

public interface UserService {

    UserProfile getByUsername(String username);

}
