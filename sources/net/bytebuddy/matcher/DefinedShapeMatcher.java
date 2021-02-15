package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ByteCodeElement.TypeDependant;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class DefinedShapeMatcher<T extends ByteCodeElement.TypeDependant<S, ?>, S extends ByteCodeElement.TypeDependant<?, ?>> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super S> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((DefinedShapeMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public DefinedShapeMatcher(ElementMatcher<? super S> elementMatcher) {
        this.matcher = elementMatcher;
    }

    public boolean matches(T t) {
        return this.matcher.matches(t.asDefined());
    }

    public String toString() {
        return "isDefinedAs(" + this.matcher + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}
