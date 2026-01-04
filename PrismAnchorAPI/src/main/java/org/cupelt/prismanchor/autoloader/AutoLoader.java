package org.cupelt.prismanchor.autoloader;

import com.google.inject.Inject;
import org.cupelt.prismanchor.autoloader.register.IRegister;
import org.cupelt.prismanchor.utils.ReflectionInitializer;

import java.util.Map;

public class AutoLoader {
    @Inject private ReflectionInitializer<AutoRegister> initializer;
    @Inject private Map<RegisterType, IRegister> registers;

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
