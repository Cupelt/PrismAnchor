package org.cupelt.prismanchor.command;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabExecutor {
    @Inject
    private Injector injector;

    public abstract CommandBuilder getCommandOptions();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        CommandBuilder builder = getCommandOptions();
        injector.injectMembers(builder);

        builder.execute(sender, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CommandBuilder builder = getCommandOptions();
        injector.injectMembers(builder);

        return builder.getExecuteTabComplete(sender, args);
    }
}
