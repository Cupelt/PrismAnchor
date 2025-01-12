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
    public void onLoad() {
        LOGGER = getLogger();
        INSTANCE = this;

        this.saveDefaultConfig();
        config = getConfig();

        new ReflectionInitializer<>(Listener.class, this)
                .reflectionForEach(listener -> {
                    getServer().getPluginManager().registerEvents(listener, this);
                });

        new ReflectionInitializer<>(AbstractCommand.class, this)
                .reflectionForEach(command -> {
                    CommandBuilder options = command.getCommandOptions();
                    getCommand(options.getName()).setExecutor(command);
                });
    }


    public static <T extends AbstractPlugin<T>> T getInstance() {
        return (T) INSTANCE;
    }
}
