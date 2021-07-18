/**
 * FileName: JsonUtil
 * Author:   haichaoyang3
 * Date:     2019/6/28 14:12
 * Description: json util tool
 * History:
 */
package com.example.paywhere.commom.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Description:〈json util tool〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
public class JsonUtil {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 忽略空bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 统一日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        // 忽略在json字符串中存在, 但在java对象中不存在对应属性的情况, 防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String objToStr(T obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("objToStr error:", e);
            return null;
        }
    }

    public static <T> T strToObj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || Objects.isNull(clazz)) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("strToObj error:", e);
            return null;
        }
    }

    public static <T> T strToObj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || Objects.isNull(typeReference)) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("strToObj error:", e);
            return null;
        }
    }


}