package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;
import org.mockito.exceptions.base.MockitoException;

public class FieldReader {
    final AccessibilityChanger changer;
    final Field field;
    final Object target;

    public FieldReader(Object obj, Field field2) {
        AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
        this.changer = accessibilityChanger;
        this.target = obj;
        this.field = field2;
        accessibilityChanger.enableAccess(field2);
    }

    public boolean isNull() {
        return read() == null;
    }

    public Object read() {
        try {
            return this.field.get(this.target);
        } catch (Exception unused) {
            throw new MockitoException("Cannot read state from field: " + this.field + ", on instance: " + this.target);
        }
    }
}
