package org.cupelt.prismanchor.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CommandTabCompleter {
    List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args);
}
