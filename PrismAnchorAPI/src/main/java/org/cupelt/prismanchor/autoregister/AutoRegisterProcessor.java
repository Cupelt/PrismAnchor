package org.cupelt.prismanchor.autoregister;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

@SupportedAnnotationTypes("org.cupelt.prismanchor.annotation.AutoRegister")
public class AutoRegisterProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<String> registers = new HashSet<>();
        
        // 1. 모든 라운드에서 클래스명을 Set에 수집
        for (Element element : roundEnv.getElementsAnnotatedWith(AutoRegister.class)) {
            registers.add(((TypeElement) element).getQualifiedName().toString());
        }

        if (roundEnv.processingOver() && !registers.isEmpty()) {
            try {
                FileObject resource = processingEnv.getFiler().createResource(
                    StandardLocation.CLASS_OUTPUT,
                    "",
                    "META-INF/autoRegister/entries.idx"
                );
                try (Writer writer = resource.openWriter()) {
                    for (String cls : registers) {
                        writer.write(cls + "\n");
                    }
                }
            } catch (IOException e) {}
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}