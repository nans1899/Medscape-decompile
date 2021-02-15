package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

public class Or implements ArgumentMatcher<Object>, Serializable {
    private final ArgumentMatcher m1;
    private final ArgumentMatcher m2;

    public Or(ArgumentMatcher<?> argumentMatcher, ArgumentMatcher<?> argumentMatcher2) {
        this.m1 = argumentMatcher;
        this.m2 = argumentMatcher2;
    }

    public boolean matches(Object obj) {
        return this.m1.matches(obj) || this.m2.matches(obj);
    }

    public String toString() {
        return "or(" + this.m1 + ", " + this.m2 + ")";
    }
}
