package com.example.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;


public class EntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    private final Filer FILER;
    private String mPackageName = null;

    EntryVisitor(Filer FILER) {
        this.FILER = FILER;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        generateJavaCode(t);
        return p;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        // Element的方法
        //    Set<Modifier> getModifiers();                             // 获取修饰符
        //    Element getEnclosingElement();                            // 获取父类元素
        //    List<? extends Element> getEnclosedElements();            // 获取子类元素
        //    <A extends Annotation> A getAnnotation(Class<A> var1);    // 获取注解
        //    TypeMirror asType();                                      // 可以根据TypeMirror，获取到Class对象
        //   可以根据TypeMirror，获取到Class对象
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXEntryActivity")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(typeMirror))
                        .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件")
                .build();
        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
