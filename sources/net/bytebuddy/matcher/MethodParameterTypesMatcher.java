package net.bytebuddy.matcher;

import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class MethodParameterTypesMatcher<T extends ParameterList<?>> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super List<? extends TypeDescription.Generic>> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((MethodParameterTypesMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public MethodParameterTypesMatcher(ElementMatcher<? super List<? extends TypeDescription.Generic>> elementMatcher) {
        this.matcher = elementMatcher;
    }

    public boolean matches(T t) {
        return this.matcher.matches(t.asTypeList());
    }

    public String toString() {
        return "hasTypes(" + this.matcher + ")";
    }
}
