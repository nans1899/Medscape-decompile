package net.bytebuddy.utility.privilege;

import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedAction;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

@HashCodeAndEqualsPlugin.Enhance
public class SetAccessibleAction<T extends AccessibleObject> implements PrivilegedAction<T> {
    private final T accessibleObject;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.accessibleObject.equals(((SetAccessibleAction) obj).accessibleObject);
    }

    public int hashCode() {
        return 527 + this.accessibleObject.hashCode();
    }

    public SetAccessibleAction(T t) {
        this.accessibleObject = t;
    }

    public T run() {
        this.accessibleObject.setAccessible(true);
        return this.accessibleObject;
    }
}
