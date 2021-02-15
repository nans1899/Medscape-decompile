package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;

public class FieldSetter {
    private FieldSetter() {
    }

    public static void setField(Object obj, Field field, Object obj2) {
        AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
        accessibilityChanger.enableAccess(field);
        try {
            field.set(obj, obj2);
            accessibilityChanger.safelyDisableAccess(field);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Access not authorized on field '" + field + "' of object '" + obj + "' with value: '" + obj2 + "'", e);
        } catch (IllegalArgumentException e2) {
            throw new RuntimeException("Wrong argument on field '" + field + "' of object '" + obj + "' with value: '" + obj2 + "', \nreason : " + e2.getMessage(), e2);
        }
    }
}
