package net.bytebuddy.matcher;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class EqualityMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    private final Object value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.value.equals(((EqualityMatcher) obj).value);
    }

    public int hashCode() {
        return 527 + this.value.hashCode();
    }

    public EqualityMatcher(Object obj) {
        this.value = obj;
    }

    public boolean matches(T t) {
        return this.value.equals(t);
    }

    public String toString() {
        return "is(" + this.value + ")";
    }
}
