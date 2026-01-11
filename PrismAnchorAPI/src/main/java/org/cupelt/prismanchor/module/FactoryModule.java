package org.cupelt.prismanchor.module;

import org.cupelt.prismanchor.command.CommandBuilder;
import org.cupelt.prismanchor.module.factory.command.CommandFactory;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().build(CommandFactory.class));
        requestStaticInjection(CommandBuilder.class);
    }
}