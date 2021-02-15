package org.mockito.internal.util;

import java.lang.reflect.Method;
import org.mockito.internal.creation.DelegatingMethod;

public class ObjectMethodsGuru {
    private ObjectMethodsGuru() {
    }

    public static boolean isToStringMethod(Method method) {
        DelegatingMethod delegatingMethod = new DelegatingMethod(method);
        return delegatingMethod.getReturnType() == String.class && delegatingMethod.getParameterTypes().length == 0 && "toString".equals(delegatingMethod.getName());
    }

    public static boolean isCompareToMethod(Method method) {
        if (!Comparable.class.isAssignableFrom(method.getDeclaringClass()) || !"compareTo".equals(method.getName()) || method.getParameterTypes().length != 1 || method.getParameterTypes()[0] != method.getDeclaringClass()) {
            return false;
        }
        return true;
    }
}
