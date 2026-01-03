package org.cupelt.prismanchor;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.autoloader.AutoLoader;
import org.cupelt.prismanchor.module.PluginModule;

import java.util.logging.Logger;

public abstract class AbstractPlugin<T extends AbstractPlugin<T>> extends JavaPlugin {

    public static FileConfiguration config;

    @Inject AutoLoader loader;

    @Override
    public void onEnable() {
        onBeforeRegister();

        if (useDefaultConfig()) {
            try {
                this.saveDefaultConfig();
                config = getConfig();
            } catch(IllegalArgumentException ignore) {}
        }

        Injector injector = Guice.createInjector(new PluginModule(this));
        injector.injectMembers(this);

        loader.initialize();

        onPluginEnable();
    }

    @Override
    public void onDisable() {
        onPluginDisable();
    }

    public abstract void onPluginEnable();
    public abstract void onPluginDisable();

    public void onBeforeRegister() {}

    public boolean useDefaultConfig() {
        return true;
    }

    public String getPrefix() {
        return "<gold>[ <red>"+this.getName()+"</red> ]";
    }
}
