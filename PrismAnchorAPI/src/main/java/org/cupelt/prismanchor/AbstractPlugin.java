package org.cupelt.prismanchor;

import com.google.inject.Guice;
import com.google.inject.Injector;
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

        if (useDefaultConfig()) {
            try {
                this.saveDefaultConfig();
                config = getConfig();
            } catch(IllegalArgumentException ignore) {}
        }

        try {
            Injector injector = Guice.createInjector(new PluginModule(this));
            AutoLoader loader = injector.getInstance(AutoLoader.class);
        } catch (Exception e) {
            throw new RuntimeException("Injector Error", e);
        }

        loader.initialize();

        AbstractPlugin.injector = injector;

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
