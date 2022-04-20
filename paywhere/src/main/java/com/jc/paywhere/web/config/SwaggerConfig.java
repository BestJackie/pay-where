package com.jc.paywhere.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * FileName: SwaggerConfig
 * Author:   haichaoyang3
 * Date:     2020/5/7 14:13
 * Description:swagger文档
 * History:
 * since: 1.0.0
 */
@Configuration
public class SwaggerConfig {
    private final String version = "1.0";
    private final String title = "spring boot 文档示例";
    private final String description = "api文档";
    private final String license = "MIT";
    private final String licenseUrl = "https://mit-license.org/";
    private final Contact contact = new Contact("haichaoyang", "www.baidu.com", "jackieyung@aliyun.com");

    @Bean
    public Docket build() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo())
                .groupName("2.X版本")
                .select().apis(RequestHandlerSelectors.basePackage("com.jc.demo.springbootdemo.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder().title(title).contact(contact).description(description).license(license).licenseUrl(licenseUrl)
                .version(version).build();
    }


}
