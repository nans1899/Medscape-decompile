package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class SetPart implements MethodPart {
    private final Cache<Annotation> cache = new ConcurrentCache();
    private final Annotation label;
    private final Annotation[] list;
    private final Method method;
    private final String name;
    private final MethodType type;

    public SetPart(MethodName methodName, Annotation annotation, Annotation[] annotationArr) {
        this.method = methodName.getMethod();
        this.name = methodName.getName();
        this.type = methodName.getType();
        this.label = annotation;
        this.list = annotationArr;
    }

    public String getName() {
        return this.name;
    }

    public Class getType() {
        return this.method.getParameterTypes()[0];
    }

    public Class getDependent() {
        return Reflector.getParameterDependent(this.method, 0);
    }

    public Class[] getDependents() {
        return Reflector.getParameterDependents(this.method, 0);
    }

    public Class getDeclaringClass() {
        return this.method.getDeclaringClass();
    }

    public Annotation getAnnotation() {
        return this.label;
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        if (this.cache.isEmpty()) {
            for (Annotation annotation : this.list) {
                this.cache.cache(annotation.annotationType(), annotation);
            }
        }
        return (Annotation) this.cache.fetch(cls);
    }

    public MethodType getMethodType() {
        return this.type;
    }

    public Method getMethod() {
        if (!this.method.isAccessible()) {
            this.method.setAccessible(true);
        }
        return this.method;
    }

    public String toString() {
        return this.method.toGenericString();
    }
}
