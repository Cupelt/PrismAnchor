package org.cupelt.prismanchor.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.cupelt.prismanchor.exception.HasNoEmptySlotException;

public class ItemUtils {

    public static boolean canItemGivable(Player target, ItemStack itemStack) {
        PlayerInventory playerInventory = target.getInventory();
        int size = 0;
        for (ItemStack item : playerInventory.getStorageContents()) {
            if (item == null || item.getType().isAir()) {
                size += itemStack.getMaxStackSize();
            } else if (item.isSimilar(itemStack)) {
                size += item.getMaxStackSize() - item.getAmount();
            }
            if (size >= itemStack.getAmount())
                return true;
        }
        return false;
    }

    public static void tryGiveItem(Player target, ItemStack itemStack) throws HasNoEmptySlotException {
        if (canItemGivable(target, itemStack)) {
            target.getInventory().addItem(itemStack);
        } else {
            throw new HasNoEmptySlotException();
        }
    }
}
