package org.mockito.plugins;

public interface AnnotationEngine {
    void process(Class<?> cls, Object obj);
}
