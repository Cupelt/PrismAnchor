package org.cupelt.prismanchor.inventory.interfaces;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public interface ClickableInventory {
    void onClickEvent(InventoryClickEvent event, @Nullable ItemStack currentItem);
}
