package org.mockito.internal.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.mockito.internal.util.Checks;

public class InstanceField {
    private final Field field;
    private FieldReader fieldReader;
    private final Object instance;

    public InstanceField(Field field2, Object obj) {
        this.field = (Field) Checks.checkNotNull(field2, "field");
        this.instance = Checks.checkNotNull(obj, "instance");
    }

    public Object read() {
        return reader().read();
    }

    public void set(Object obj) {
        FieldSetter.setField(this.instance, this.field, obj);
    }

    public boolean isNull() {
        return reader().isNull();
    }

    public boolean isAnnotatedBy(Class<? extends Annotation> cls) {
        return this.field.isAnnotationPresent(cls);
    }

    public boolean isSynthetic() {
        return this.field.isSynthetic();
    }

    public <A extends Annotation> A annotation(Class<A> cls) {
        return this.field.getAnnotation(cls);
    }

    public Field jdkField() {
        return this.field;
    }

    private FieldReader reader() {
        if (this.fieldReader == null) {
            this.fieldReader = new FieldReader(this.instance, this.field);
        }
        return this.fieldReader;
    }

    public String name() {
        return this.field.getName();
    }

    public String toString() {
        return name();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InstanceField instanceField = (InstanceField) obj;
        if (!this.field.equals(instanceField.field) || !this.instance.equals(instanceField.instance)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.field.hashCode() * 31) + this.instance.hashCode();
    }
}
