package org.mockito.internal.matchers;

import java.io.Serializable;
import java.lang.Comparable;
import org.mockito.ArgumentMatcher;

public abstract class CompareTo<T extends Comparable<T>> implements ArgumentMatcher<T>, Serializable {
    private final T wanted;

    /* access modifiers changed from: protected */
    public abstract String getName();

    /* access modifiers changed from: protected */
    public abstract boolean matchResult(int i);

    public CompareTo(T t) {
        this.wanted = t;
    }

    public final boolean matches(T t) {
        if (t != null && t.getClass().isInstance(this.wanted)) {
            return matchResult(t.compareTo(this.wanted));
        }
        return false;
    }

    public final String toString() {
        return getName() + "(" + this.wanted + ")";
    }
}
