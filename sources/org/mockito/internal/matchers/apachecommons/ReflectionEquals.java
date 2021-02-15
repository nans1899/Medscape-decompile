package org.mockito.internal.matchers.apachecommons;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

public class ReflectionEquals implements ArgumentMatcher<Object>, Serializable {
    private final String[] excludeFields;
    private final Object wanted;

    public ReflectionEquals(Object obj, String... strArr) {
        this.wanted = obj;
        this.excludeFields = strArr;
    }

    public boolean matches(Object obj) {
        return EqualsBuilder.reflectionEquals(this.wanted, obj, this.excludeFields);
    }

    public String toString() {
        return "refEq(" + this.wanted + ")";
    }
}
