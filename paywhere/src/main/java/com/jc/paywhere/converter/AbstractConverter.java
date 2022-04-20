package com.jc.paywhere.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * FileName: AbstractConverter
 *
 * @author: admin
 * Date:     2021-09-17 14:38
 * Description:
 * Since: 1.0.0
 */

public abstract class AbstractConverter<E, V> implements Converter<E, V> {
    protected ConvertConfigurer convertConfigurer = new ConvertConfigurer();

    private boolean convertKeyOnly;

    private boolean enableBackReference;

    private boolean shallow;


    @SuppressWarnings("unchecked")
    public <T extends AbstractConverter<E, V>> T keyOnly(boolean enable) {
        this.convertKeyOnly = enable;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractConverter<E, V>> T backReference(boolean enable) {
        this.enableBackReference = enable;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractConverter<E, V>> T shallow(boolean enable) {
        this.shallow = enable;
        return (T) this;
    }

    protected boolean convertKeyOnly() {
        return convertKeyOnly;
    }

    protected boolean backReference() {
        return enableBackReference;
    }

    protected boolean isShallow() {
        return shallow;
    }

    public List<E> convertFrom(Collection<V> valueObjects) {
        if (Objects.isNull(valueObjects))
            return null;
        List<E> entities = new ArrayList<>();
        valueObjects.forEach(vo -> entities.add(convertFrom(vo)));
        return entities;
    }

    public List<V> convertTo(Collection<? extends E> entities) {
        if (Objects.isNull(entities))
            return null;
        List<V> valueObjects = new ArrayList<>();
        entities.forEach(entity -> valueObjects.add(convertTo(entity)));
        return valueObjects;
    }


}
