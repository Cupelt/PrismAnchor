package org.cupelt.prismanchor.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target(ElementType.TYPE)
public @interface AutoRegister {
    public String moduleName() default "";
}
