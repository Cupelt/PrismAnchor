package org.cupelt.prismanchor.autoloader.processor;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import org.cupelt.prismanchor.autoloader.annotation.EventRegister;

@SupportedAnnotationTypes("org.cupelt.prismanchor.autoloader.annotation.EventRegister")
public class EventRegisterProcessor extends BaseRegisterProcessor {

    @Override
    public String getIdentifier() {
        return "events";
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return EventRegister.class;
    }

}
