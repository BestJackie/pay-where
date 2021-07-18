/**
 * FileName: TestServiceImpl
 * Author:   haichaoyang3
 * Date:     2019/7/2 11:43
 * Description: test service
 * History:
 */
package com.example.paywhere.service.impl;

import com.jc.demo.springbootdemo.commom.model.ServerResponse;
import com.jc.demo.springbootdemo.service.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Description:〈test service〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }
}