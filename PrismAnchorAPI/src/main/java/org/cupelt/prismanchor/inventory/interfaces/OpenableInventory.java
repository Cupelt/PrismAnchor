package org.cupelt.prismanchor.inventory.interfaces;

import org.bukkit.event.inventory.InventoryOpenEvent;

public interface OpenableInventory {
    void onOpen(InventoryOpenEvent event);
}
