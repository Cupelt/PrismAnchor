package org.cupelt.prismanchor.example.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.command.CommandPerformer;
import org.cupelt.prismanchor.example.inventory.CustomInventory;

import org.cupelt.prismanchor.inject.Inject;

public class InventoryCommand implements CommandPerformer {
    @Inject
    private JavaPlugin plugin;


    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        player.openInventory(new CustomInventory(plugin).getInventory());
    }
}
