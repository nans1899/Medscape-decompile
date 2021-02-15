package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.text.ValuePrinter;

public class Same implements ArgumentMatcher<Object>, Serializable {
    private final Object wanted;

    public Same(Object obj) {
        this.wanted = obj;
    }

    public boolean matches(Object obj) {
        return this.wanted == obj;
    }

    public String toString() {
        return "same(" + ValuePrinter.print(this.wanted) + ")";
    }
}
