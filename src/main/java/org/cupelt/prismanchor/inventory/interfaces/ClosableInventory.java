package org.cupelt.prismanchor.inventory.interfaces;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface ClosableInventory {
    void onCloseEvent(InventoryCloseEvent event);
}
