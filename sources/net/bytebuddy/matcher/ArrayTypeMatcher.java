package net.bytebuddy.matcher;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class ArrayTypeMatcher<T extends TypeDefinition> extends ElementMatcher.Junction.AbstractBase<T> {
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    public int hashCode() {
        return 17;
    }

    public String toString() {
        return "isArray()";
    }

    public boolean matches(T t) {
        return t.isArray();
    }
}
