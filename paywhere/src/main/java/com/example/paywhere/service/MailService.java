package com.example.paywhere.service;

import com.example.paywhere.dao.entity.UserProfile;

import java.util.Map;

public interface MailService {

    void sendMail(Map date);

    void sendRemindMail(UserProfile userProfile);

    void sendMail(String code, String email);

}
