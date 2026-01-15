package org.cupelt.prismanchor.autoloader.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import org.cupelt.prismanchor.autoloader.annotation.Component;

import javax.tools.FileObject;
import javax.tools.StandardLocation;

@SupportedAnnotationTypes("org.cupelt.prismanchor.autoloader.annotation.Component")
public abstract class BaseRegisterProcessor extends AbstractProcessor {
    public abstract String getIdentifier();
    public abstract Class<? extends Annotation> getAnnotationClass();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) return false;

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(getAnnotationClass());
        Set<? extends Element> components = roundEnv.getElementsAnnotatedWith(Component.class);

        if (elements.isEmpty()) return false;

        try {
            FileObject resource = processingEnv.getFiler().createResource(
                StandardLocation.CLASS_OUTPUT, "", "META-INF/prismanchor/" + getIdentifier() + ".list");
            
            processingEnv.getMessager().printMessage(Kind.NOTE, "Creating resource at: " + resource.toUri());

            try (PrintWriter writer = new PrintWriter(resource.openWriter())) {
                for (Element element : elements) {
                    if (element instanceof TypeElement typeElement) {

                        String componentName;
                        String className;
                        if (!components.contains(element)) {
                            componentName = "default";
                            className = typeElement.getQualifiedName().toString();
                        }
                        else {
                            componentName = getComponentName(typeElement.getAnnotation(Component.class)); 
                            className = typeElement.getQualifiedName().toString();
                        }
                        
                        writer.println(componentName + "/" + className);
                    }
                }
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Kind.WARNING, "Failed to create resource: " + e.getMessage());
        }
        return true;
    }

    protected String getComponentName(Annotation annotation) {
        if (annotation instanceof Component component) {
            String value = component.value();
            return (value == null || value.isBlank()) ? "default" : value;
        }
        return "default";
    }
    
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
}
