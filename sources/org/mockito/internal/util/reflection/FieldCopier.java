package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;

public class FieldCopier {
    public <T> void copyValue(T t, T t2, Field field) throws IllegalAccessException {
        field.set(t2, field.get(t));
    }
}
