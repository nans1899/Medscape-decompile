package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

public class Not implements ArgumentMatcher<Object>, Serializable {
    private final ArgumentMatcher matcher;

    public Not(ArgumentMatcher<?> argumentMatcher) {
        this.matcher = argumentMatcher;
    }

    public boolean matches(Object obj) {
        return !this.matcher.matches(obj);
    }

    public String toString() {
        return "not(" + this.matcher + ")";
    }
}
