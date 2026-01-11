package org.cupelt.prismanchor.autoloader.processor;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import org.cupelt.prismanchor.autoloader.annotation.CommandRegister;

@SupportedAnnotationTypes("org.cupelt.prismanchor.autoloader.annotation.CommandRegister")
public class CommandRegisterProcessor extends BaseRegisterProcessor {

    @Override
    public String getIdentifier() {
        return "commands";
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return CommandRegister.class;
    }
}
