package org.cupelt.prismanchor.inject;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.AbstractPlugin;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class PluginModule extends AbstractModule {

    private final AbstractPlugin plugin;

    public PluginModule(AbstractPlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(this.plugin);
        bind(JavaPlugin.class).toInstance(this.plugin);
        bind(ClassLoader.class).toInstance(this.plugin.classLoader);

        bind(Logger.class)
            .annotatedWith(Names.named("pluginLogger"))
            .toInstance(this.plugin.getLogger());
    }
}
