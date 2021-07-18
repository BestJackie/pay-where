/**
 * FileName: LoginLogServiceimpl
 * Author:   haichaoyang3
 * Date:     2019/7/2 11:43
 * Description: login log
 * History:
 */
package com.example.paywhere.service.impl;


import com.jc.demo.springbootdemo.dao.mapper.LoginLogMapper;
import com.jc.demo.springbootdemo.dao.model.LoginLog;
import com.jc.demo.springbootdemo.service.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:〈login log〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
@Service
public class LoginLogServiceimpl implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void insert(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }


}