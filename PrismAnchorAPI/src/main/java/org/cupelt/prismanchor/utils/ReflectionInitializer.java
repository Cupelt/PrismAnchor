package org.cupelt.prismanchor.utils;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;
import java.util.function.Consumer;

public class ReflectionInitializer<T> {

    private final Class<T> type;
    private final JavaPlugin plugin;

    private final Set<Class<? extends T>> classes;

    @Inject
    public ReflectionInitializer(JavaPlugin plugin, @Assisted Class<T> type) {
        this.type = type;
        this.plugin = plugin;

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(plugin.getClass().getPackageName()));
        this.classes = reflections.getSubTypesOf(type);
    }

    public void reflectionForEach(Consumer<T> initializer) {
        for (Class<? extends T> clazz : eventClasses) {
            try {
                T instance = injector.getInstance(clazz);
                initializer.accept(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
