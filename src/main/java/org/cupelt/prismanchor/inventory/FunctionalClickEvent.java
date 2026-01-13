package org.cupelt.prismanchor.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface FunctionalClickEvent {
    void execute(InventoryClickEvent event, Player player);
}
