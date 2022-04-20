package com.jc.paywhere.service;

import com.jc.paywhere.dao.entity.UserEntity;

import java.util.Map;

public interface MailService {

    void sendMail(Map date);

    void sendRemindMail(UserEntity userEntity);

    void sendMail(String code, String email);

}
