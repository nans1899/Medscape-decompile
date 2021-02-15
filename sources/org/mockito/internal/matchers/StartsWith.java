package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

public class StartsWith implements ArgumentMatcher<String>, Serializable {
    private final String prefix;

    public StartsWith(String str) {
        this.prefix = str;
    }

    public boolean matches(String str) {
        return str != null && str.startsWith(this.prefix);
    }

    public String toString() {
        return "startsWith(\"" + this.prefix + "\")";
    }
}
