package org.cupelt.prismanchor.command;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractCommand extends Command {
    @Inject
    private Injector injector;
    @Inject
    private JavaPlugin plugin;

    protected AbstractCommand() {
        super("anonymous_command");
        setName(getCommandOptions().getName());
        setLabel(getCommandOptions().getName());
        setDescription(getCommandOptions().getDescription());
        setAliases(getCommandOptions().getAliases());
        setPermission(getCommandOptions().getPermission());
        setUsage(getCommandOptions().getUsage());
    }

    public abstract CommandBuilder getCommandOptions();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Test Passed!!");
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
