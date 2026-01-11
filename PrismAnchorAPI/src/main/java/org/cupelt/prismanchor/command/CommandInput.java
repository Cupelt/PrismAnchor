package org.cupelt.prismanchor.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandInput {
    private final CommandSender sender;
    private final String label;
    private final String[] args;

    protected CommandInput(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        this.sender = sender;
        this.label = commandLabel;
        this.args = args;
    }
}
