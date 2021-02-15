package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.ClassLoader;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class ClassLoaderParentMatcher<T extends ClassLoader> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ClassLoader classLoader;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.classLoader.equals(((ClassLoaderParentMatcher) obj).classLoader);
    }

    public int hashCode() {
        return 527 + this.classLoader.hashCode();
    }

    public ClassLoaderParentMatcher(ClassLoader classLoader2) {
        this.classLoader = classLoader2;
    }

    public boolean matches(T t) {
        for (T t2 = this.classLoader; t2 != null; t2 = t2.getParent()) {
            if (t2 == t) {
                return true;
            }
        }
        if (t == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "isParentOf(" + this.classLoader + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}
