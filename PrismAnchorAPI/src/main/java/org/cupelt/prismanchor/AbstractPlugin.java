package org.cupelt.prismanchor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;
import org.cupelt.prismanchor.inject.PluginModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class AbstractPlugin<T extends AbstractPlugin<T>> extends JavaPlugin {

    public static FileConfiguration config;

    public static Injector injector;
    public ClassLoader classLoader;

    @Override
    public void onEnable() {
        this.classLoader = getClassLoader();

        onBeforeRegister();

        if (useDefaultConfig()) {
            try {
                this.saveDefaultConfig();
                config = getConfig();
            } catch(IllegalArgumentException ignore) {}
        }

        this.injector = Guice.createInjector(new PluginModule(this, this.getLogger()));

        onPluginEnable();
    }

    @Override
    public void onDisable() {
        onPluginDisable();
    }

    public void registerCommand(AbstractCommand command) {
        CommandBuilder options = command.getCommandOptions();
        getCommand(options.getName()).setExecutor(command);
    }

    public abstract void onPluginEnable();
    public abstract void onPluginDisable();

    public void onBeforeRegister() {}

    public boolean useDefaultConfig() {
        return true;
    }

    /***
     * Implemntable
     * @return
     */
    public String getPrefix() {
        return "<gold>[ <red>"+this.getName()+"</red> ]";
    }
}
