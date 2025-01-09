package org.cupelt.prismanchor.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryItemExecutorBuilder<T extends AbstractInventory> extends InventoryItemExecutor<T> {

    ItemStack item;
    FunctionalClickEvent executor;

    public InventoryItemExecutorBuilder(T inventory) {
        super(inventory);
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setOnClick(FunctionalClickEvent executor) {
        this.executor = executor;
    }

    @Override
    protected ItemStack getItem() {
        return item;
    }

    @Override
    protected void onClick(InventoryClickEvent event, Player player) {
        executor.execute(event, player);
    }
}
