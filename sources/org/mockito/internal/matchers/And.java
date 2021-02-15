package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

public class And implements ArgumentMatcher<Object>, Serializable {
    private ArgumentMatcher m1;
    private ArgumentMatcher m2;

    public And(ArgumentMatcher<?> argumentMatcher, ArgumentMatcher<?> argumentMatcher2) {
        this.m1 = argumentMatcher;
        this.m2 = argumentMatcher2;
    }

    public boolean matches(Object obj) {
        return this.m1.matches(obj) && this.m2.matches(obj);
    }

    public String toString() {
        return "and(" + this.m1 + ", " + this.m2 + ")";
    }
}
