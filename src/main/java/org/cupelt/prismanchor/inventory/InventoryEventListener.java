package org.cupelt.prismanchor.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.cupelt.prismanchor.inventory.interfaces.ClickableInventory;
import org.cupelt.prismanchor.inventory.interfaces.ClosableInventory;
import org.cupelt.prismanchor.inventory.interfaces.OpenableInventory;
import org.cupelt.prismanchor.inventory.interfaces.PickableInventory;

public class InventoryEventListener implements Listener {

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof OpenableInventory open) {
            open.onOpen(event);
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof ClosableInventory close) {
            close.onCloseEvent(event);
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {

        if (!(event.getInventory().getHolder() instanceof AbstractInventory holder)) {
            return;
        }

        ItemStack clickedItem = event.getCurrentItem();
        event.setCancelled(!(holder instanceof PickableInventory));

        if (holder instanceof ClickableInventory clickable) {
            clickable.onClickEvent(event, clickedItem);
        }

        if (event.getClickedInventory() instanceof PlayerInventory) {
            event.setCancelled(false);
        }

        InventoryItemExecutor<? extends AbstractInventory> executor = holder.getItemExecutor(clickedItem);
        if (executor != null) {
            executor.onClick(event, ((Player) event.getWhoClicked()));
            event.setCancelled(true);
        }
    }
}
