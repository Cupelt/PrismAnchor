package org.cupelt.prismanchor.example.command;

import javax.inject.Inject;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.cupelt.prismanchor.command.CommandPerformer;
import org.cupelt.prismanchor.example.inventory.CustomInventory;

public class InventoryCommand implements CommandPerformer {

    @Inject
    Plugin plugin;

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        player.openInventory(new CustomInventory(plugin).getInventory());
    }
}
