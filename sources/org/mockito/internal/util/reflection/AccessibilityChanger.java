package org.mockito.internal.util.reflection;

import java.lang.reflect.AccessibleObject;

public class AccessibilityChanger {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Boolean wasAccessible = null;

    public void safelyDisableAccess(AccessibleObject accessibleObject) {
        try {
            accessibleObject.setAccessible(this.wasAccessible.booleanValue());
        } catch (Throwable unused) {
        }
    }

    public void enableAccess(AccessibleObject accessibleObject) {
        this.wasAccessible = Boolean.valueOf(accessibleObject.isAccessible());
        accessibleObject.setAccessible(true);
    }
}
