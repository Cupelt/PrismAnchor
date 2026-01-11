package org.cupelt.prismanchor.example.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.cupelt.prismanchor.inventory.InventoryItemExecutor;
import org.cupelt.prismanchor.item.ItemBuilder;
import org.cupelt.prismanchor.utils.ChatUtils;

public class TeleportPvpItem extends InventoryItemExecutor<CustomInventory> {
    public TeleportPvpItem(CustomInventory instance) {
        super(instance);
    }

    @Override
    protected ItemStack getItem() {
        return new ItemBuilder(Material.DIAMOND_SWORD)
                .setDisplayName("<red><b>Go to PVP!")
                .setMeta(itemMeta -> itemMeta.setUnbreakable(true))
                .build();
     }

    @Override
    protected void onClick(InventoryClickEvent event, Player player) {
        player.closeInventory();
        player.sendMessage(ChatUtils.minimessage("<red>PVP world is not implemented!"));
    }
}
