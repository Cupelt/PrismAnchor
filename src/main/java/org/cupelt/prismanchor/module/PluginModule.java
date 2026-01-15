package org.cupelt.prismanchor.module;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.AbstractPlugin;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class PluginModule<T extends AbstractPlugin<T>> extends AbstractModule {

    private final T plugin;

    public PluginModule(T plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(this.plugin);
        bind(JavaPlugin.class).toInstance(this.plugin);
        bind((Class<T>) plugin.getClass()).toInstance(this.plugin);

        install(new FactoryModule());
        install(new AutoLoaderModule());

        bind(Logger.class)
                .annotatedWith(Names.named("pluginLogger"))
                .toInstance(this.plugin.getLogger());
    }
}
