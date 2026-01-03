package org.cupelt.prismanchor.autoloader.register;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.inject.Inject;
import org.cupelt.prismanchor.autoloader.AutoRegister;

public class EventRegister implements IRegister {

    @Inject
    private JavaPlugin plugin;

    @Override
    public void register(AutoRegister source) {
        Listener listener = (Listener) source;
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

}
