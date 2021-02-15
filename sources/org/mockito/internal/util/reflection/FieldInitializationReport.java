package org.mockito.internal.util.reflection;

public class FieldInitializationReport {
    private final Object fieldInstance;
    private final boolean wasInitialized;
    private final boolean wasInitializedUsingConstructorArgs;

    public FieldInitializationReport(Object obj, boolean z, boolean z2) {
        this.fieldInstance = obj;
        this.wasInitialized = z;
        this.wasInitializedUsingConstructorArgs = z2;
    }

    public Object fieldInstance() {
        return this.fieldInstance;
    }

    public boolean fieldWasInitialized() {
        return this.wasInitialized;
    }

    public boolean fieldWasInitializedUsingContructorArgs() {
        return this.wasInitializedUsingConstructorArgs;
    }

    public Class<?> fieldClass() {
        Object obj = this.fieldInstance;
        if (obj != null) {
            return obj.getClass();
        }
        return null;
    }
}
