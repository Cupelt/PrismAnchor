package org.cupelt.prismanchor.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InventoryUtils {

    public static void closeInventoryEveryone(List<HumanEntity> players) {
        for (HumanEntity p : new ArrayList<>(players)) {
            p.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
        }
    }

    public static int get2DIndexing(int x, int y) {
        return (y * 9) + x;
    }

    public static void fillInventory(Consumer<Integer> consumer, int x1, int y1, int x2, int y2) {
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                consumer.accept(get2DIndexing(x, y));
            }
        }
    }

    public static void fillBoxInventory(Consumer<Integer> consumer, int x1, int y1, int x2, int y2) {
        for (int x = x1; x <= x2; x++) {
            consumer.accept(get2DIndexing(x, y1));
            consumer.accept(get2DIndexing(x, y2));
        }

        for (int y = y1; y <= y2; y++) {
            consumer.accept(get2DIndexing(x1, y));
            consumer.accept(get2DIndexing(x2, y));
        }
    }
}
