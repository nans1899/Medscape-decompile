package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

public class NotNull implements ArgumentMatcher<Object>, Serializable {
    public static final NotNull NOT_NULL = new NotNull();

    public boolean matches(Object obj) {
        return obj != null;
    }

    public String toString() {
        return "notNull()";
    }

    private NotNull() {
    }
}
