package com.example.paywhere.web.config;

import org.slf4j.Logger;

import javax.annotation.PreDestroy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * FileName: TerminateBean
 * Author:   haichaoyang3
 * Date:     2020/1/19 15:15
 * Description:
 * History:
 * since: 1.0.0
 */
public class TerminateBean {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TerminateBean.class);

    @PreDestroy
    public void preDestory() {
        log.info("TerminalBean is destoryed");
    }


    public static void main(String[] args) {
        URLClassLoader extClassLoader = ((URLClassLoader) ClassLoader.getSystemClassLoader().getParent());
        System.out.println("extClassLoader 的加载路径");
        URL[] extUrls = extClassLoader.getURLs();
        Arrays.stream(extUrls).forEach(System.out::println);

        URLClassLoader appClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        System.out.println("appClassLoader 的加载路径");
        URL[] appUrls = appClassLoader.getURLs();
        Arrays.stream(appUrls).forEach(System.out::println);
    }
}
