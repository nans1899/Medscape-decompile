package org.mockito.internal.matchers;

public interface ContainsExtraTypeInfo {
    String toStringWithType();

    boolean typeMatches(Object obj);
}
