package org.cupelt.prismanchor.module.factory.utils;

import org.cupelt.prismanchor.utils.ReflectionInitializer;

public interface ReflectionFactory {
    ReflectionInitializer<?> create(Class<?> clazz);
}
