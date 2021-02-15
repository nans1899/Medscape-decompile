package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class LenientCopyTool {
    FieldCopier fieldCopier = new FieldCopier();

    public <T> void copyToMock(T t, T t2) {
        copy(t, t2, t.getClass());
    }

    public <T> void copyToRealObject(T t, T t2) {
        copy(t, t2, t.getClass());
    }

    private <T> void copy(T t, T t2, Class<?> cls) {
        while (true) {
            Class<? super Object> cls2 = cls;
            if (cls2 != Object.class) {
                copyValues(t, t2, cls2);
                cls2 = cls2.getSuperclass();
            } else {
                return;
            }
        }
    }

    private <T> void copyValues(T t, T t2, Class<?> cls) {
        for (Field field : cls.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
                try {
                    accessibilityChanger.enableAccess(field);
                    this.fieldCopier.copyValue(t, t2, field);
                } catch (Throwable unused) {
                }
                accessibilityChanger.safelyDisableAccess(field);
            }
        }
    }
}
