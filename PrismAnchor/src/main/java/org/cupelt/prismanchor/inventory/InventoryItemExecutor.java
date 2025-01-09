package org.cupelt.prismanchor.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class InventoryItemExecutor<T> {

    private final T instance;

    public InventoryItemExecutor(T instance) {
        this.instance = instance;
    }

    protected abstract ItemStack getItem();
    protected abstract void onClick(InventoryClickEvent event, Player player);

    protected T getInventoryInstance() {
        return instance;
    }
}
