package org.cupelt.prismanchor.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.cupelt.prismanchor.AbstractPlugin;

import java.util.ArrayList;
import java.util.List;

public class ChatUtils {
    public static String toColorString(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static List<String> toColorList(List<String> list) {
        List<String> stringList = new ArrayList<>();

        for (String string : list) {
            stringList.add(toColorString(string));
        }

        return stringList;
    }

    public static void eraseChat(Player player) {
        Component comp = Component.text("");
        for (int i = 0; i < 120; i++) {
            comp = comp.appendNewline();
        }
        player.sendMessage(comp);
    }

    public static Component minimessage(String format, AbstractPlugin plugin) {
        return MiniMessage.miniMessage().deserialize((plugin != null ? plugin.getPrefix() : "") + " " + format);
    }

    public static Component minimessage(String format) {
        return MiniMessage.miniMessage().deserialize(format);
    }

    public static String ColorStringToMiniMessage(String colorString) {
        Component component = LegacyComponentSerializer.legacySection().deserialize(colorString);
        colorString = MiniMessage.miniMessage().serialize(component);

        return colorString.replaceAll("\\\\<", "<");
    }
}