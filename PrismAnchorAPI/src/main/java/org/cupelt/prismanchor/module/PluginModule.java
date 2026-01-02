package org.cupelt.prismanchor.module;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import com.google.inject.AbstractModule;

public class PluginModule extends AbstractModule {

    private Plugin plugin;
    private Logger logger;

    public PluginModule(Plugin plugin, Logger logger) {
        this.plugin = plugin;
        this.logger = logger;
    }
    
    @Override
    protected void configure() {
        bind(Plugin.class)
            .toInstance(this.plugin);

        bind(Logger.class)
            .toInstance(this.logger);
    }
}
