package org.cupelt.prismanchor.autoloader;

import com.google.inject.Inject;
import com.google.inject.Injector;

import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.utils.CommandUtils;
import org.cupelt.prismanchor.utils.ReflectionInitializer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
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
        Map<String, Set<Class<? extends AbstractCommand>>> commands = loadMETAData("commands", AbstractCommand.class);
        commands.forEach((componentName, commandSet) -> {
            // when component is disable then continue

            commandSet.forEach(clazz -> {
                AbstractCommand command = injector.getInstance(clazz);
                CommandUtils.register(command);
            });
        });
    }

    public <T> Map<String, Set<Class<? extends T>>> loadMETAData(String identifier, Class<T> type) {
        String resourcePath = "META-INF/prismanchor/" + identifier + ".list";

        Map<String, Set<Class<? extends T>>> classes = new HashMap<>();
        
        try (InputStream is = plugin.getResource(resourcePath)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    String[] args = line.split("/");
                    String componentName = args[0];
                    String className = args[1];

                    try {
                        Class<?> clazz = Class.forName(className);
                        if (type.isAssignableFrom(clazz)) {
                            classes.computeIfAbsent(componentName, (ignored) -> new HashSet<>())
                                .add((Class<? extends T>) clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        logger.warning("Class not found: " + className);
                    }
                }
            }
        } catch (Exception e) {
            logger.severe("Error loading registered classes: " + e.getMessage());
            e.printStackTrace();
        }

        return classes;
    }
}
