package org.cupelt.prismanchor.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import org.cupelt.prismanchor.autoloader.AutoRegister;

@SupportedAnnotationTypes("org.cupelt.prismanchor.autoloader.AutoRegister")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class CommandRegisterProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 1. 프로세서가 시작되었는지 확인
        processingEnv.getMessager().printMessage(Kind.NOTE, "--- PrismAnchorAPI Processor Started ---");

        if (roundEnv.processingOver()) return false;

        // 2. 스캔 대상 어노테이션이 발견되었는지 확인
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(AutoRegister.class);

        if (elements.isEmpty()) return false;

        try {
            FileObject resource = processingEnv.getFiler().createResource(
                StandardLocation.CLASS_OUTPUT, "", "META-INF/prismanchor/commands.list");
            
            processingEnv.getMessager().printMessage(Kind.NOTE, "Creating resource at: " + resource.toUri());

            try (PrintWriter writer = new PrintWriter(resource.openWriter())) {
                for (Element element : elements) {
                    if (element instanceof TypeElement typeElement) {
                        String className = typeElement.getQualifiedName().toString();
                        writer.println(className);
                    }
                }
            }
        } catch (IOException e) {
            // 오류 발생 시 중단되지 않도록 경고만 출력
            processingEnv.getMessager().printMessage(Kind.WARNING, "Failed to create resource: " + e.getMessage());
        }
        return true;
    }
}
