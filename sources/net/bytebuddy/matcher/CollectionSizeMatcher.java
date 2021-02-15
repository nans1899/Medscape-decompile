package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.Iterable;
import java.util.Collection;
import java.util.Iterator;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class CollectionSizeMatcher<T extends Iterable<?>> extends ElementMatcher.Junction.AbstractBase<T> {
    private final int size;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.size == ((CollectionSizeMatcher) obj).size;
    }

    public int hashCode() {
        return 527 + this.size;
    }

    public CollectionSizeMatcher(int i) {
        this.size = i;
    }

    public boolean matches(T t) {
        if (!(t instanceof Collection)) {
            Iterator it = t.iterator();
            int i = 0;
            while (it.hasNext()) {
                it.next();
                i++;
            }
            if (i == this.size) {
                return true;
            }
            return false;
        } else if (((Collection) t).size() == this.size) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "ofSize(" + this.size + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}
