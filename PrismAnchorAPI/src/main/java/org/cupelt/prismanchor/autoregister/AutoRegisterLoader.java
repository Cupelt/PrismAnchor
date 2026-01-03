package org.cupelt.prismanchor.autoregister;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

public class AutoRegisterLoader {
    @Inject
    private ClassLoader classLoader;

    public void initialize() {
        Set<Class<? extends AutoRegister>> classes = load();

        List<AutoRegister> instanceList = new ArrayList<>();
        for (Class<? extends AutoRegister> clazz : classes) {
            try {
                instanceList.add(clazz.getDeclaredConstructor().newInstance());
            } catch (Exception ignored) {}
        }

        instanceList.sort(Comparator.comparing((AutoRegister item) -> item.registerType() != AutoRegister.RegisterType.MODULE));

        for (AutoRegister register : instanceList) {

            // if (register.registerType() != AutoRegister.RegisterType.MODULE) {
            //     if (!register.moduleName().isEmpty() && register) {
                    
            //     }
            // }
            register.registerType().register(register);
        }
    }

    public Set<Class<? extends AutoRegister>> load() {
        Set<Class<? extends AutoRegister>> classes = new HashSet<>();

        try {
            Enumeration<URL> resources = classLoader
                    .getResources("META-INF/autoRegister/entries.idx");

            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    String className;
                    while ((className = reader.readLine()) != null) {
                        className = className.trim();
                        if (className.isEmpty()) continue;

                        try {
                            Class<?> clazz = Class.forName(className, false, classLoader);
                            classes.add((Class<? extends AutoRegister>) clazz);
                            
                        } catch (ClassNotFoundException e) {
                            System.err.println("Warning: Could not find class " + className);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("AutoRegister initialize failed", e);
        }

        return classes;
    }
}
