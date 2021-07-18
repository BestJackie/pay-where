package com.example.paywhere.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * FileName: Generator
 * Author:   haichaoyang
 * Date:     2020/11/17 3:29 下午
 * Description:
 * History:
 * since: 1.0.0
 */

public class Generator {

    public static void main(String[] args) throws Exception {
        ArrayList<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        InputStream is = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        warnings.forEach(System.out::println);
    }
}
