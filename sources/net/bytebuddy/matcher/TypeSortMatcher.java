package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class TypeSortMatcher<T extends TypeDefinition> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDefinition.Sort> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((TypeSortMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public TypeSortMatcher(ElementMatcher<? super TypeDefinition.Sort> elementMatcher) {
        this.matcher = elementMatcher;
    }

    public boolean matches(T t) {
        return this.matcher.matches(t.getSort());
    }

    public String toString() {
        return "ofSort(" + this.matcher + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}
