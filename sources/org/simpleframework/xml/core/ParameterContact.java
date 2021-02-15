package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

abstract class ParameterContact<T extends Annotation> implements Contact {
    protected final Constructor factory;
    protected final int index;
    protected final T label;
    protected final Annotation[] labels;
    protected final Class owner;

    public Object get(Object obj) {
        return null;
    }

    public abstract String getName();

    public boolean isReadOnly() {
        return false;
    }

    public void set(Object obj, Object obj2) {
    }

    public ParameterContact(T t, Constructor constructor, int i) {
        this.labels = constructor.getParameterAnnotations()[i];
        this.owner = constructor.getDeclaringClass();
        this.factory = constructor;
        this.index = i;
        this.label = t;
    }

    public Annotation getAnnotation() {
        return this.label;
    }

    public Class getType() {
        return this.factory.getParameterTypes()[this.index];
    }

    public Class getDependent() {
        return Reflector.getParameterDependent(this.factory, this.index);
    }

    public Class[] getDependents() {
        return Reflector.getParameterDependents(this.factory, this.index);
    }

    public Class getDeclaringClass() {
        return this.owner;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        for (A a : this.labels) {
            if (a.annotationType().equals(cls)) {
                return a;
            }
        }
        return null;
    }

    public String toString() {
        return String.format("parameter %s of constructor %s", new Object[]{Integer.valueOf(this.index), this.factory});
    }
}
