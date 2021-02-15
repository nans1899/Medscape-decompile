package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

public class Any implements ArgumentMatcher<Object>, VarargMatcher, Serializable {
    public static final Any ANY = new Any();

    public boolean matches(Object obj) {
        return true;
    }

    public String toString() {
        return "<any>";
    }
}
