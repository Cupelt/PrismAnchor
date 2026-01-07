package org.cupelt.prismanchor.module;

import org.cupelt.prismanchor.utils.ReflectionInitializer;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(ReflectionInitializer.class));
    }
}