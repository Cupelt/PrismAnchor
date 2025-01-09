package org.cupelt.prismanchor.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.cupelt.prismanchor.others.ChatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(ItemStack itemstack) {
        this.item = itemstack;
        this.meta = item.getItemMeta();
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

    public ItemBuilder addFlag(ItemFlag... flag) {
        meta.addItemFlags(flag);

        return this;
    }

    public ItemBuilder setOwner(OfflinePlayer player) {
        if (!(meta instanceof SkullMeta) || player == null || item.getType() != Material.PLAYER_HEAD) {
            return this;
        }

        ((SkullMeta) meta).setOwningPlayer(player);
        return this;
    }

    public ItemBuilder setMeta(Consumer<ItemMeta> meta) {
        meta.accept(this.meta);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        List<Component> loreList = meta.lore();
        if (loreList == null)
            loreList = new ArrayList<>();

        loreList.add(ChatUtils.minimessage(
                ChatUtils.ColorStringToMiniMessage(lore)
        ).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE));
        meta.lore(loreList);

        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        meta.lore(lore.stream()
                .map(l -> ChatUtils.minimessage(
                        ChatUtils.ColorStringToMiniMessage(l)
                ).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE))
                .toList());
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        meta.displayName(ChatUtils.minimessage(
                ChatUtils.ColorStringToMiniMessage(displayName)
        ).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE));
        return this;
    }

    public ItemBuilder setPersistentData(NamespacedKey key, PersistentDataType type, Object o) {
        meta.getPersistentDataContainer().set(key, type, o);
        return this;
    }

}
