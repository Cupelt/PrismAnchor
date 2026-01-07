package org.cupelt.prismanchor.module.factory.utils;

public interface ReflectionFactory<T> {
    ReflectionInitializer<T> create(Class<T> clazz);
}
