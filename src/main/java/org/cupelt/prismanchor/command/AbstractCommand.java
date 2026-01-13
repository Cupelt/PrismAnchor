package org.cupelt.prismanchor.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.AbstractPlugin;
import org.jetbrains.annotations.NotNull;

import com.google.inject.Inject;
import com.google.inject.Injector;

public abstract class AbstractCommand extends Command {
    private Injector injector;
    @Inject
    private JavaPlugin plugin;

    @Inject
    public AbstractCommand() {
        super("anonymous_command");
        this.injector = AbstractPlugin.getInjector();

        CommandBuilder builder = getCommandOptions();

        setName(builder.getName());
        setLabel(builder.getName());
        setDescription(builder.getDescription());
        setAliases(builder.getAliases());
        setPermission(builder.getPermission());
        setUsage( builder.getUsage());
    }

    public abstract CommandBuilder getCommandOptions();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        CommandBuilder builder = getCommandOptions();
        injector.injectMembers(builder);

        builder.execute(sender, args);
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias,
            @NotNull String[] args) throws IllegalArgumentException {
        CommandBuilder builder = getCommandOptions();
        injector.injectMembers(builder);

        return builder.getExecuteTabComplete(sender, args);
    }
}
