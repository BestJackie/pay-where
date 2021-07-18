package com.example.paywhere.service;


import com.jc.demo.springbootdemo.commom.model.ServerResponse;

public interface TestService {
    ServerResponse testIdempotence();

    ServerResponse accessLimit();
}
