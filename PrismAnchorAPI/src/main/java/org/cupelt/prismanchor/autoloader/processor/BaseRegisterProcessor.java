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

        // 2. 스캔 대상 어노테이션이 발견되었는지 확인
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
                            // 어노테이션의 value 값을 가져와 파일명으로 사용 (예: @Component("auth") -> auth)
                            componentName = getComponentName(typeElement.getAnnotation(Component.class)); 
                            className = typeElement.getQualifiedName().toString();
                        }
                        
                        writer.println(componentName + "/" + className);
                    }
                }
            }
        } catch (IOException e) {
            // 오류 발생 시 중단되지 않도록 경고만 출력
            processingEnv.getMessager().printMessage(Kind.WARNING, "Failed to create resource: " + e.getMessage());
        }
        return true;
    }


    // @Override
    // public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    //     if (roundEnv.processingOver()) return false;
// 
    //     Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(getAnnotationClass());
    //     Set<? extends Element> components = roundEnv.getElementsAnnotatedWith(Component.class);
    //     if (elements.isEmpty()) return false;
// 
    //     // <ComponentName, List<elements>>
    //     Map<String, List<String>> groupedElements = new HashMap<>();
// 
    //     for (Element element : elements) {
    //         if (element instanceof TypeElement typeElement) {
    //             String fileName;
    //             String className;
// 
    //             if (!components.contains(element)) {
    //                 fileName = "default";
    //                 className = typeElement.getQualifiedName().toString();
    //             }
    //             else {
    //                 // 어노테이션의 value 값을 가져와 파일명으로 사용 (예: @Component("auth") -> auth)
    //                 fileName = getComponentName(typeElement); 
    //                 className = typeElement.getQualifiedName().toString();
    //             }
// 
    //             groupedElements.computeIfAbsent(fileName, k -> new ArrayList<>()).add(className);
    //         }
    //     }
// 
    //     // 2. 그룹화된 각 파일명마다 개별 리소스 파일을 생성합니다.
    //     groupedElements.forEach((componentName, classNames) -> {
    //         try {
    //             FileObject resource = processingEnv.getFiler().createResource(
    //                 StandardLocation.CLASS_OUTPUT, 
    //                 "", 
    //                 "META-INF/prismanchor/" + componentName + "/" + getIdentifier() + ".list"
    //             );
// 
    //             try (PrintWriter writer = new PrintWriter(resource.openWriter())) {
    //                 for (String className : classNames) {
    //                     writer.println(className);
    //                 }
    //             }
    //         } catch (IOException e) {
    //             processingEnv.getMessager().printMessage(Kind.WARNING, "Failed to create resource for " + componentName + ": " + e.getMessage());
    //         }
    //     });
// 
    //     return true;
    // }

    // 어노테이션에서 이름을 추출하는 헬퍼 메소드
    protected String getComponentName(Annotation annotation) {
        if (annotation instanceof Component component) {
            // @Component("worker") 에서 "worker"를 추출
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
