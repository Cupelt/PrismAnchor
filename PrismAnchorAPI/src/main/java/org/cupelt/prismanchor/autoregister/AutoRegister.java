package org.cupelt.prismanchor.autoregister;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.cupelt.prismanchor.autoregister.registers.ComandRegister;
import org.cupelt.prismanchor.autoregister.registers.IRegister;
import org.jetbrains.annotations.NotNull;

@Target(ElementType.TYPE)
public @interface AutoRegister {
    public String moduleName() default "";
    @NotNull
    public RegisterType registerType(); 

    public enum RegisterType {
        MODULE(new ComandRegister()),
        COMMAND(new ComandRegister()),
        EVENT(new ComandRegister());

        public final IRegister _register;

        private RegisterType(IRegister register) {
            this._register = register;
        }

        public void register(AutoRegister obj) {
            _register.register(obj);
        }
    }
}
