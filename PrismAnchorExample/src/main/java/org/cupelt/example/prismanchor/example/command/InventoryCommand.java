package org.cupelt.example.prismanchor.example.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cupelt.prismanchor.command.CommandPerformer;
import org.cupelt.example.prismanchor.example.inventory.CustomInventory;

public class InventoryCommand implements CommandPerformer {

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }

        player.openInventory(new CustomInventory(Test.getInstance()).getInventory());
    }
}
