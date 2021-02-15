package net.bytebuddy.matcher;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class BooleanMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    private final boolean matches;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matches == ((BooleanMatcher) obj).matches;
    }

    public int hashCode() {
        return true + (this.matches ? 1 : 0);
    }

    public BooleanMatcher(boolean z) {
        this.matches = z;
    }

    public boolean matches(T t) {
        return this.matches;
    }

    public String toString() {
        return Boolean.toString(this.matches);
    }
}
