package org.mockito.internal.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Matcher;
import org.mockito.internal.util.reflection.GenericTypeExtractor;

public class MatcherGenericTypeExtractor {
    public static Class<?> genericTypeOfMatcher(Class<?> cls) {
        return GenericTypeExtractor.genericTypeOf(cls, BaseMatcher.class, Matcher.class);
    }
}
