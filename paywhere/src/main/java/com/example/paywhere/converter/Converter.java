package com.example.paywhere.converter;

/**
 * FileName: Conventer
 *
 * @author: admin
 * Date:     2021-09-17 14:36
 * Since: 1.0.0
 */
public interface Converter<E, V> {
    E convertFrom(V v);

    V convertTo(E e);
}
