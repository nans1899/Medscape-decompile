package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodDetail {
    private final Annotation[] list;
    private final Method method;
    private final String name;

    public MethodDetail(Method method2) {
        this.list = method2.getDeclaredAnnotations();
        this.name = method2.getName();
        this.method = method2;
    }

    public Annotation[] getAnnotations() {
        return this.list;
    }

    public Method getMethod() {
        return this.method;
    }

    public String getName() {
        return this.name;
    }
}
