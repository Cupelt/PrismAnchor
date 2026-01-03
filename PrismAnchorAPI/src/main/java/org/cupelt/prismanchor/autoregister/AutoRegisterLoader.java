package org.cupelt.prismanchor.autoregister;

public class AutoRegisterLoader {
    public void initialize() {
        new ReflectionInitializer<>(AutoRegister.class)
                    .reflectionForEach(register -> {
                        register.registerType().register(register);
                    });
    }
}
