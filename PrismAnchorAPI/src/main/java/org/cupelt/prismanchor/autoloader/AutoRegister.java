package org.cupelt.prismanchor.autoloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.jetbrains.annotations.NotNull;


@Target(ElementType.TYPE)
public @interface AutoRegister {
    public String moduleName() default "";
    @NotNull
    public RegisterType registerType();
}