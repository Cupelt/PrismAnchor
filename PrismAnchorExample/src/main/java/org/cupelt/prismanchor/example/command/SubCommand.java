package org.cupelt.prismanchor.example.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cupelt.prismanchor.command.CommandPerformer;
import org.cupelt.prismanchor.command.CommandTabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SubCommand implements CommandPerformer, CommandTabCompleter {
    @Override
    public void perform(CommandSender sender, String[] args) {
        sender.sendMessage("Test SubCommand");
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }
}
