package net.bytebuddy.matcher;

import java.util.Iterator;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class CollectionElementMatcher<T> extends ElementMatcher.Junction.AbstractBase<Iterable<? extends T>> {
    private final int index;
    private final ElementMatcher<? super T> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CollectionElementMatcher collectionElementMatcher = (CollectionElementMatcher) obj;
        return this.index == collectionElementMatcher.index && this.matcher.equals(collectionElementMatcher.matcher);
    }

    public int hashCode() {
        return ((527 + this.index) * 31) + this.matcher.hashCode();
    }

    public CollectionElementMatcher(int i, ElementMatcher<? super T> elementMatcher) {
        this.index = i;
        this.matcher = elementMatcher;
    }

    public boolean matches(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        for (int i = 0; i < this.index; i++) {
            if (!it.hasNext()) {
                return false;
            }
            it.next();
        }
        if (!it.hasNext() || !this.matcher.matches(it.next())) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "with(" + this.index + " matches " + this.matcher + ")";
    }
}
