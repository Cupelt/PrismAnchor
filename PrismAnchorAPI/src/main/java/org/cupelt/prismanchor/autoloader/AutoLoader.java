package org.cupelt.prismanchor.autoloader;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.cupelt.prismanchor.autoloader.register.IRegister;

import java.util.Map;

public class AutoLoader {
    @Inject
    private Injector injector;
    @Inject private ReflectionInitializer<AutoRegister> initializer;
    @Inject private Map<RegisterType, IRegister> registers;

    public void initialize() {

        this.initializer = new ReflectionInitializer<>(AutoRegister.class);
        injector.injectMembers(this);

        this.initializer.reflectionForEach(instance -> {
            RegisterType type = instance.registerType();

            IRegister register = registers.get(type);
            if (register != null) {
                register.register(instance);
            }
        });
    }
}
