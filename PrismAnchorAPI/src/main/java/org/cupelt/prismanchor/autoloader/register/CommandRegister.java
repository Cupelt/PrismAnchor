package org.cupelt.prismanchor.autoloader.register;

import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.autoloader.AutoRegister;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;

import com.google.inject.Inject;

public class CommandRegister implements IRegister {

    @Inject
    private JavaPlugin plugin;

    @Override
    public void register(AutoRegister source) {
        AbstractCommand command = (AbstractCommand) source;

        CommandBuilder options = command.getCommandOptions();
        plugin.getCommand(options.getName()).setExecutor(command);
    }

}