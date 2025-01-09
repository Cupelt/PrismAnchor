package org.cupelt.example.prismanchor.example.inventory;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.cupelt.prismanchor.inventory.AbstractInventory;
import org.cupelt.prismanchor.inventory.InventorySizeEnum;
import org.cupelt.prismanchor.inventory.InventoryUtils;
import org.cupelt.prismanchor.item.ItemBuilder;
import org.jetbrains.annotations.NotNull;

public class CustomInventory extends AbstractInventory {

    public CustomInventory(@NotNull Plugin plugin) {
        super(plugin);
    }

    @Override
    protected String getTitle() {
        return "<red>TEST Inventory!";
    }

    @Override
    protected @NotNull InventorySizeEnum getInventorySize() {
        return InventorySizeEnum.XL;
    }

    @Override
    protected void initialize() {
        InventoryUtils.fillBoxInventory(index -> {
            inventory.setItem(index,
                    new ItemBuilder(Material.BARRIER)
                            .setDisplayName("<red><i>Empty!")
                            .build()
            );
        }, 0, 0, 8, 5);
        setItemExecutor(4, 2, new TeleportPvpItem(this));
    }
}
