package org.cupelt.prismanchor.processer;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.cupelt.prismanchor.annotation.AutoRegister;

@SupportedAnnotationTypes("org.cupelt.prismanchor.annotation.AutoRegister")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class AutoRegisterProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(AutoRegister.class)) {
            // 1. 클래스 이름을 가져와서
            String className = ((TypeElement) element).getQualifiedName().toString();
            // 2. META-INF/services/your.package.MyCommand 파일에 기록하는 로직
            // (이 과정이 다소 복잡하므로 보통 AutoService를 그대로 사용합니다.)
        }
        return true;
    }
}