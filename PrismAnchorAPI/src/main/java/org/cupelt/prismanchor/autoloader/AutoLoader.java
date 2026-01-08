package org.cupelt.prismanchor.autoloader;

import com.google.inject.Inject;
import org.cupelt.prismanchor.autoloader.register.IRegister;
import org.cupelt.prismanchor.module.factory.utils.ReflectionFactory;
import org.cupelt.prismanchor.utils.ReflectionInitializer;

import java.util.Map;

public class AutoLoader {
    private final ReflectionInitializer<AutoRegister> initializer;
    
    @Inject
    private Map<RegisterType, IRegister> registers;

    @Inject
    @SuppressWarnings("unchecked")
    public AutoLoader(ReflectionFactory factory) {
        this.initializer = (ReflectionInitializer<AutoRegister>) factory.create(AutoRegister.class);
    }

    public void initialize() {
        this.initializer.reflectionForEach(instance -> {
            RegisterType type = instance.registerType();

            IRegister register = registers.get(type);
            if (register != null) {
                register.register(instance);
            }
        });
    }
}
