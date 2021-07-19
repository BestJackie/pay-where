package com.example.paywhere.service;


import com.example.paywhere.commom.model.ServerResponse;


import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    ServerResponse createToken();

    void checkToken(HttpServletRequest request);

    String getToken(HttpServletRequest request, String headKey);
}
