package org.mockito.internal.matchers;

import java.io.Serializable;
import java.util.regex.Pattern;
import org.mockito.ArgumentMatcher;

public class Matches implements ArgumentMatcher<Object>, Serializable {
    private final Pattern pattern;

    public Matches(String str) {
        this(Pattern.compile(str));
    }

    public Matches(Pattern pattern2) {
        this.pattern = pattern2;
    }

    public boolean matches(Object obj) {
        return (obj instanceof String) && this.pattern.matcher((String) obj).matches();
    }

    public String toString() {
        return "matches(\"" + this.pattern.pattern().replaceAll("\\\\", "\\\\\\\\") + "\")";
    }
}
