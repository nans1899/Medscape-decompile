package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

class FieldDetail {
    private final Field field;
    private final Annotation[] list;
    private final String name;

    public FieldDetail(Field field2) {
        this.list = field2.getDeclaredAnnotations();
        this.name = field2.getName();
        this.field = field2;
    }

    public Annotation[] getAnnotations() {
        return this.list;
    }

    public Field getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }
}
