package org.cupelt.prismanchor.autoregister.registers;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.autoregister.AutoRegister;

import com.google.inject.Inject;

public class EventRegister implements IRegister {

    @Inject
    private JavaPlugin plugin; 

    @Override
    public void register(AutoRegister source) {
        Listener listener = (Listener) source;
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

}
