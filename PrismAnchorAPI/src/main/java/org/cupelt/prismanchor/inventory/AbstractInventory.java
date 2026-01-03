package org.cupelt.prismanchor.inventory;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.cupelt.prismanchor.utils.ChatUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractInventory implements InventoryHolder {

    protected final Plugin plugin;

    protected Inventory inventory;
    protected Map<String, InventoryItemExecutor<? extends AbstractInventory>> EXECUTOR_MAP = new HashMap<>();
    protected final InventoryHolder prevInv;

    public AbstractInventory(@NotNull Plugin plugin, @Nullable InventoryHolder prevInv) {
        this.plugin = plugin;
        this.prevInv = prevInv;
    }

    public AbstractInventory(@NotNull Plugin plugin) {
        this(plugin, null);
    }

    @Override
    public @NotNull Inventory getInventory() {
        if (inventory == null) {
            this.inventory = Bukkit.createInventory(
                    this, getInventorySize().getSize(),
                    LegacyComponentSerializer.legacySection().serialize(
                            ChatUtils.minimessage(ChatUtils.ColorStringToMiniMessage(getTitle()))
                    )
            );
            initialize();
        }

        return inventory;
    }

    protected abstract String getTitle();
    @NotNull
    protected abstract InventorySizeEnum getInventorySize();

    /**
     * Initialize inventory such as put item, init var, etc.
     * This method must be implemented by subclasses to provide the specific inventory setup.
     */
    protected abstract void initialize();

    protected void setItemExecutor(int slot, @NotNull InventoryItemExecutor<? extends AbstractInventory> executor) {
        String identifier = UUID.randomUUID().toString();
        EXECUTOR_MAP.put(identifier, executor);

        ItemStack item = executor.getItem();
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            NamespacedKey key = new NamespacedKey(plugin, "executor_id");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, identifier);
            item.setItemMeta(meta);
        }

        inventory.setItem(slot, item);
    }

    protected void setItemExecutor(int x, int y, @NotNull InventoryItemExecutor<? extends AbstractInventory> executor) {
        setItemExecutor(InventoryUtils.get2DIndexing(x, y), executor);
    }

    protected InventoryItemExecutor<? extends AbstractInventory> getItemExecutor(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "executor_id");
        String identifier = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);

        return identifier != null ? EXECUTOR_MAP.get(identifier) : null;
    }

    protected void setTitle(InventoryEvent event, String title) {
        event.getView().setTitle(
                LegacyComponentSerializer.legacyAmpersand().serialize(
                        ChatUtils.minimessage(title)
                )
        );
    }

    /**
     * Returns to the previous inventory if it exists, otherwise closes the current inventory.
     */
    public void returnToPreviousInventory(HumanEntity player) {
        if (prevInv == null) {
            player.closeInventory();
            return;
        }

        player.openInventory(prevInv.getInventory());
    }

}
