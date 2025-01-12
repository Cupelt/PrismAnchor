package org.cupelt.prismanchor.others;

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
//        Map<String, String> colorMap = new HashMap<>();
//        colorMap.put("(&|§)0", "<black>");
//        colorMap.put("(&|§)1", "<dark_blue>");
//        colorMap.put("(&|§)2", "<dark_green>");
//        colorMap.put("(&|§)3", "<dark_aqua>");
//        colorMap.put("(&|§)4", "<dark_red>");
//        colorMap.put("(&|§)5", "<dark_purple>");
//        colorMap.put("(&|§)6", "<gold>");
//        colorMap.put("(&|§)7", "<gray>");
//        colorMap.put("(&|§)8", "<dark_gray>");
//        colorMap.put("(&|§)9", "<blue>");
//        colorMap.put("(&|§)a", "<green>");
//        colorMap.put("(&|§)b", "<aqua>");
//        colorMap.put("(&|§)c", "<red>");
//        colorMap.put("(&|§)d", "<light_purple>");
//        colorMap.put("(&|§)e", "<yellow>");
//        colorMap.put("(&|§)f", "<white>");
//        colorMap.put("(&|§)k", "<obf>");
//        colorMap.put("(&|§)l", "<b>");
//        colorMap.put("(&|§)m", "<st>");
//        colorMap.put("(&|§)n", "<u>");
//        colorMap.put("(&|§)o", "<i>");
//        colorMap.put("(&|§)r", "<reset>");
//
//        for (Map.Entry<String, String> entry : colorMap.entrySet()) {
//            colorString = colorString.replaceAll(entry.getKey(), entry.getValue());
//        }
//
//        colorString = colorString.replaceAll("&#([0-9a-fA-F]{6})", "<#$1>");

        Component component = LegacyComponentSerializer.legacySection().deserialize(colorString);
        colorString = MiniMessage.miniMessage().serialize(component);

        return colorString.replaceAll("\\\\<", "<");
    }
}