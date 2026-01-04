package org.cupelt.prismanchor.utils;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;
import java.util.function.Consumer;

public class ReflectionInitializer<T> {

    private final Class<T> type;

    @Inject
    private JavaPlugin plugin;

    @Inject
    private Injector injector;

    public ReflectionInitializer(Class<T> type) {
        this.type = type;
    }

    public void reflectionForEach(Consumer<T> initializer) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(plugin.getClass().getPackageName()));
        Set<Class<? extends T>> eventClasses = reflections.getSubTypesOf(type);

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
