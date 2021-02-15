package org.mockito.internal.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.plugins.AnnotationEngine;

public class IndependentAnnotationEngine implements AnnotationEngine, org.mockito.configuration.AnnotationEngine {
    private final Map<Class<? extends Annotation>, FieldAnnotationProcessor<?>> annotationProcessorMap = new HashMap();

    public IndependentAnnotationEngine() {
        registerAnnotationProcessor(Mock.class, new MockAnnotationProcessor());
        registerAnnotationProcessor(Captor.class, new CaptorAnnotationProcessor());
    }

    private Object createMockFor(Annotation annotation, Field field) {
        return forAnnotation(annotation).process(annotation, field);
    }

    private <A extends Annotation> FieldAnnotationProcessor<A> forAnnotation(A a) {
        if (this.annotationProcessorMap.containsKey(a.annotationType())) {
            return this.annotationProcessorMap.get(a.annotationType());
        }
        return new FieldAnnotationProcessor<A>() {
            public Object process(A a, Field field) {
                return null;
            }
        };
    }

    private <A extends Annotation> void registerAnnotationProcessor(Class<A> cls, FieldAnnotationProcessor<A> fieldAnnotationProcessor) {
        this.annotationProcessorMap.put(cls, fieldAnnotationProcessor);
    }

    public void process(Class<?> cls, Object obj) {
        for (Field field : cls.getDeclaredFields()) {
            boolean z = false;
            for (Annotation annotation : field.getAnnotations()) {
                Object createMockFor = createMockFor(annotation, field);
                if (createMockFor != null) {
                    throwIfAlreadyAssigned(field, z);
                    try {
                        FieldSetter.setField(obj, field, createMockFor);
                        z = true;
                    } catch (Exception e) {
                        throw new MockitoException("Problems setting field " + field.getName() + " annotated with " + annotation, e);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void throwIfAlreadyAssigned(Field field, boolean z) {
        if (z) {
            throw Reporter.moreThanOneAnnotationNotAllowed(field.getName());
        }
    }
}
