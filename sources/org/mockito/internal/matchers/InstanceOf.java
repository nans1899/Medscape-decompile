package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.util.Primitives;

public class InstanceOf implements ArgumentMatcher<Object>, Serializable {
    private final Class<?> clazz;
    private String description;

    public InstanceOf(Class<?> cls) {
        this(cls, "isA(" + cls.getCanonicalName() + ")");
    }

    public InstanceOf(Class<?> cls, String str) {
        this.clazz = cls;
        this.description = str;
    }

    public boolean matches(Object obj) {
        return obj != null && (Primitives.isAssignableFromWrapper(obj.getClass(), this.clazz) || this.clazz.isAssignableFrom(obj.getClass()));
    }

    public String toString() {
        return this.description;
    }

    public static class VarArgAware extends InstanceOf implements VarargMatcher {
        public VarArgAware(Class<?> cls) {
            super(cls);
        }

        public VarArgAware(Class<?> cls, String str) {
            super(cls, str);
        }
    }
}
