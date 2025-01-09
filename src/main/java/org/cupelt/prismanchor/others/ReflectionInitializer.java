package org.cupelt.prismanchor.others;

import org.cupelt.prismanchor.AbstractPlugin;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;
import java.util.function.Consumer;

public class ReflectionInitializer<T> {

    private final Class<T> type;
    private final AbstractPlugin plugin;

    public ReflectionInitializer(Class<T> type, AbstractPlugin plugin) {
        this.type = type;
        this.plugin = plugin;
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
