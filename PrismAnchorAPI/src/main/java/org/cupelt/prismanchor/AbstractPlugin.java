package org.cupelt.prismanchor;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.autoloader.AutoLoader;
import org.cupelt.prismanchor.module.PluginModule;

public abstract class AbstractPlugin extends JavaPlugin {

    public static FileConfiguration config;
    private static Injector injector;

    @Override
    public void onEnable() {
        onBeforeRegister();
        Logger.getLogger("org.cupelt.prismanchor.inject.assistedinject.FactoryProvider2").setLevel(Level.SEVERE);

        if (useDefaultConfig()) {
            try {
                this.saveDefaultConfig();
                config = getConfig();
            } catch(IllegalArgumentException ignore) {}
        }

        Injector injector = Guice.createInjector(new PluginModule(this));
        AbstractPlugin.injector = injector;

        AutoLoader loader = injector.getInstance(AutoLoader.class);

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

    public static Injector getInjector() {
        if (injector == null) {
            throw new RuntimeException("Injector is not initialized!");
        }
        return injector;
    }
}
