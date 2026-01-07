package org.cupelt.prismanchor.module.factory.utils;

import org.cupelt.prismanchor.utils.ReflectionInitializer;

public interface ReflectionFactory<T> {
    ReflectionInitializer<T> create(Class<T> clazz);
}
