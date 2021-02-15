package net.bytebuddy.matcher;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class DescriptorMatcher<T extends ByteCodeElement> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<String> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((DescriptorMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public DescriptorMatcher(ElementMatcher<String> elementMatcher) {
        this.matcher = elementMatcher;
    }

    public boolean matches(T t) {
        return this.matcher.matches(t.getDescriptor());
    }

    public String toString() {
        return "hasDescriptor(" + this.matcher + ")";
    }
}
