package org.mockito.internal.invocation;

import java.lang.reflect.Method;
import org.mockito.ArgumentMatcher;

public class TypeSafeMatching implements ArgumentMatcherAction {
    private static final ArgumentMatcherAction TYPE_SAFE_MATCHING_ACTION = new TypeSafeMatching();

    private TypeSafeMatching() {
    }

    public static ArgumentMatcherAction matchesTypeSafe() {
        return TYPE_SAFE_MATCHING_ACTION;
    }

    public boolean apply(ArgumentMatcher argumentMatcher, Object obj) {
        return isCompatible(argumentMatcher, obj) && argumentMatcher.matches(obj);
    }

    private static boolean isCompatible(ArgumentMatcher<?> argumentMatcher, Object obj) {
        if (obj == null) {
            return true;
        }
        return getArgumentType(argumentMatcher).isInstance(obj);
    }

    private static Class<?> getArgumentType(ArgumentMatcher<?> argumentMatcher) {
        for (Method method : argumentMatcher.getClass().getMethods()) {
            if (isMatchesMethod(method)) {
                return method.getParameterTypes()[0];
            }
        }
        throw new NoSuchMethodError("Method 'matches(T)' not found in ArgumentMatcher: " + argumentMatcher + " !\r\n Please file a bug with this stack trace at: https://github.com/mockito/mockito/issues/new ");
    }

    private static boolean isMatchesMethod(Method method) {
        if (method.getParameterTypes().length == 1 && !method.isBridge()) {
            return "matches".equals(method.getName());
        }
        return false;
    }
}
