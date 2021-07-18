package com.example.paywhere.service;

import com.jc.demo.springbootdemo.dao.model.SysUser;

import java.util.Map;

public interface MailService {

    void sendMail(Map date);

    void sendMail(SysUser sysUser);

    void sendMail(String code, String email);

}
