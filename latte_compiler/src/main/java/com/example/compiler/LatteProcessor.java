package com.example.compiler;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.TypeName;

import java.lang.annotation.Annotation;
import java.security.acl.AclNotFoundException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import jdk.nashorn.internal.runtime.Source;

/**
 * AbstractProcessor 类是由compile 'com.google.auto.service:auto-service:1.0-rc3' 生成的文件
 * Android上的SPI实现.这是一个注解处理器，是Google开发的，用来生成META-INF/services/javax.annotation.processing.Processor文件的。
 * APT(Annotation Process Tool)，是一种在代码编译时处理注解，
 * 按照一定的规则，生成相应的java文件，多用于对自定义注解的处理，
 * 目前比较流行的Dagger2, ButterKnife, EventBus3都是采用APT技术，对运行时的性能影响很小。
 */

@SuppressWarnings("unused")
@AutoService(Process.class)
public class LatteProcessor extends AbstractProcessor {
    /**
     * 当前apt 适用于微信登录，注册和支付绕过包名限制
     * process 方法代表的是，有注解就都会进来
     *
     * @param set
     * @param env
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        generateEntryCode(env);
        return false;
    }

    // 指定processortype
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    /**
     * 添加Annotation注解类
     *
     * @return
     */
    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }



    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor =
                new EntryVisitor(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        //  getElementsAnnotatedWith(Annotation.Class) 获取所有注解了Annotation的Element对象

        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {

           //Element.getAnnotationMirrors()    返回直接存在于此元素上的注释。
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }
}
