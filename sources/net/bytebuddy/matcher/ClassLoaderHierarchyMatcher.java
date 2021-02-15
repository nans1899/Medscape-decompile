package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.ClassLoader;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class ClassLoaderHierarchyMatcher<T extends ClassLoader> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super ClassLoader> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((ClassLoaderHierarchyMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public ClassLoaderHierarchyMatcher(ElementMatcher<? super ClassLoader> elementMatcher) {
        this.matcher = elementMatcher;
    }

    public boolean matches(T t) {
        while (t != null) {
            if (this.matcher.matches(t)) {
                return true;
            }
            t = t.getParent();
        }
        return this.matcher.matches(null);
    }

    public String toString() {
        return "hasChild(" + this.matcher + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}
