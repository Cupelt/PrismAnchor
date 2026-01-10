package org.cupelt.prismanchor.autoloader;

import com.google.inject.Inject;
import com.google.inject.Injector;

import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.module.factory.utils.ReflectionFactory;
import org.cupelt.prismanchor.utils.CommandUtils;
import org.cupelt.prismanchor.utils.ReflectionInitializer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class AutoLoader {
    @Inject
    private Logger logger;
    private final JavaPlugin plugin;

    @Inject
    private Injector injector;

    @Inject
    public AutoLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        logger.info("Loading AutoLoader...");
        Set<Class<? extends AbstractCommand>> commands = loadMETAData("META-INF/prismanchor/commands.list", AbstractCommand.class);
        commands.forEach(clazz -> {
            AbstractCommand command = injector.getInstance(clazz);
            CommandUtils.register(command);
        });


    }

    public <T> Set<Class<? extends T>> loadMETAData(String resourcePath, Class<T> type) {
        // String resourcePath = "META-INF/prismanchor/services.list";

        Set<Class<? extends T>> classes = new HashSet<>();
        
        try (InputStream is = plugin.getResource(resourcePath)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String className;
                while ((className = reader.readLine()) != null) {
                    className = className.trim();
                    if (className.isEmpty()) continue;

                    try {
                        Class<?> clazz = Class.forName(className);
                        // 읽어온 클래스가 찾고자 하는 타입(T)의 하위 타입인지 확인
                        if (type.isAssignableFrom(clazz)) {
                            classes.add((Class<? extends T>) clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        logger.warning("Class not found: " + className);
                    }
                }
            }
        } catch (Exception e) {
            logger.severe("Error loading registered classes: " + e.getMessage());
        }

        return classes;
    }
}
