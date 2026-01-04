package org.cupelt.prismanchor.utils;

import com.google.inject.Injector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.AbstractPlugin;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;

public class CommandUtils {
    public static void register(AbstractCommand command) {

        Injector injector = AbstractPlugin.getInjector();
        JavaPlugin plugin = injector.getInstance(JavaPlugin.class);

        CommandBuilder options = command.getCommandOptions();
        injector.injectMembers(command);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Bukkit.getCommandMap().getKnownCommands().put(options.getName(), command);
            Bukkit.getServer().getCommandMap().register(plugin.getName(), command);
        }, 1);
    }
}
