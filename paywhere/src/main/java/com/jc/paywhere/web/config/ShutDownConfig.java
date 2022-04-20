package com.jc.paywhere.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName: ShutDownConfig
 * Author:   haichaoyang3
 * Date:     2020/1/19 15:17
 * Description:
 * History:
 * since: 1.0.0
 */
@Configuration
public class ShutDownConfig {
    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }
}
