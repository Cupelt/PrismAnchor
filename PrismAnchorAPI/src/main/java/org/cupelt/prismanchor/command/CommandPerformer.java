package org.cupelt.prismanchor.command;

import org.bukkit.command.CommandSender;

public interface CommandPerformer {

    void perform(CommandSender sender, String[] args);
}
