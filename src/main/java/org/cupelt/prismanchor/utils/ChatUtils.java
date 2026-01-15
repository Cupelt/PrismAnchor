package org.cupelt.prismanchor.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.cupelt.prismanchor.AbstractPlugin;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ChatUtils {
    @Inject
    private static AbstractPlugin<?> plugin;

    private static List<TagResolver> defaultResolvers = new ArrayList<>(Arrays.asList(
        Placeholder.parsed("plugin", plugin.getPrefix())
    ));

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

    public static Component minimessage(String format) {
        return MiniMessage.miniMessage().deserialize(format, defaultResolvers.toArray(TagResolver[]::new));
    }

    public static Component minimessage(String format, TagResolver... resolver) {
        return MiniMessage.miniMessage().deserialize(
            format, 
            Stream.concat(
                defaultResolvers.stream(), 
                Stream.of(resolver)
            ).toArray(TagResolver[]::new)
        );
    }
    
    public static String ColorStringToMiniMessage(String colorString) {
        Component component = LegacyComponentSerializer.legacySection().deserialize(colorString);
        colorString = MiniMessage.miniMessage().serialize(component);

        return colorString.replaceAll("\\\\<", "<");
    }
}