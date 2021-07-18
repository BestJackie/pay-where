/**
 * FileName: ConfigUtil
 * Author:   haichaoyang3
 * Date:     2019/6/28 15:13
 * Description: config util
 * History:
 */
package com.example.paywhere.commom.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Description:〈config util〉
 *
 * @author haichaoyang3
 * @create 2019/6/28
 * @since 1.0.0
 */
public class ConfigUtil {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigUtil() {
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public static void updateProperties(String key, String value) {
        properties.setProperty(key, value);
    }

}