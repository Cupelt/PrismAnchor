package org.cupelt.prismanchor.autoregister;

import java.util.Set;
import java.util.function.Consumer;

import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import com.google.inject.Inject;

public class ReflectionInitializer<T> {

    private final Class<T> type;

    @Inject
    private Plugin plugin;

    public ReflectionInitializer(Class<T> type) {
        this.type = type;
    }

    public void reflectionForEach(Consumer<T> initializer) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(plugin.getClass().getPackageName()));
        Set<Class<? extends T>> eventClasses = reflections.getSubTypesOf(type);

        for (Class<? extends T> clazz : eventClasses) {
            try {
                T instance = clazz.getDeclaredConstructor().newInstance();
                initializer.accept(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
