package org.cupelt.prismanchor.inject;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.AbstractPlugin;

import com.google.inject.AbstractModule;

public class PluginModule extends AbstractModule {

    private AbstractPlugin plugin;
    private Logger logger;

    public PluginModule(AbstractPlugin plugin, Logger logger) {
        this.plugin = plugin;
        this.logger = logger;
    }
    
    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(this.plugin);
        bind(JavaPlugin.class).toInstance(this.plugin);
        bind(ClassLoader.class).toInstance(this.plugin.classLoader);

        bind(Logger.class).toInstance(this.logger);
    }
}
