package com.example.paywhere.converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FileName: ConvertConfigurer
 *
 * @author: admin
 * Date:     2021-09-17 14:40
 * Description:
 * Since: 1.0.0
 */

public class ConvertConfigurer {
    private final Map<Class<?>, Converter<?, ?>> converters = new ConcurrentHashMap<>();

    public ConvertConfigurer registerConverter(Class<?> clazz, Converter<?, ?> converter) {
        converters.put(clazz, converter);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Converter<?, ?>> T getConverter(Class<?> clazz) {
        return (T) converters.get(clazz);
    }

}
