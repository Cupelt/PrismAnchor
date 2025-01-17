package org.cupelt.prismanchor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;
import org.cupelt.prismanchor.others.ReflectionInitializer;

import java.util.logging.Logger;

public abstract class AbstractPlugin<T extends AbstractPlugin<T>> extends JavaPlugin {

    public static FileConfiguration config;
    public static Logger LOGGER;

    private static AbstractPlugin<?> INSTANCE;

    @Override
    public void onEnable() {
        onBeforeRegister();

        LOGGER = getLogger();
        INSTANCE = this;

        if (useDefaultConfig()) {
            try {
                this.saveDefaultConfig();
                config = getConfig();
            } catch(IllegalArgumentException ignore) {}
        }

        if (useAutoEventRegister()) {
            new ReflectionInitializer<>(Listener.class, this)
                    .reflectionForEach(listener -> {
                        getServer().getPluginManager().registerEvents(listener, this);
                    });
        }

        if (useAutoCommandRegister()) {
            new ReflectionInitializer<>(AbstractCommand.class, this)
                    .reflectionForEach(command -> {
                        CommandBuilder options = command.getCommandOptions();
                        getCommand(options.getName()).setExecutor(command);
                    });
        }

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
    public boolean useAutoEventRegister() {
        return true;
    }
    public boolean useAutoCommandRegister() {
        return true;
    }

    public String getPrefix() {
        return "<gold>[ <red>"+getInstance().getName()+"</red> ]";
    }

    public static <T extends AbstractPlugin<T>> T getInstance() {
        return (T) INSTANCE;
    }
}
