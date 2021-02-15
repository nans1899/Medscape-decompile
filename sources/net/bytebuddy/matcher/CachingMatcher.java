package net.bytebuddy.matcher;

import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance(permitSubclassEquality = true)
public class CachingMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
    protected final ConcurrentMap<? super T, Boolean> map;
    private final ElementMatcher<? super T> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CachingMatcher) && this.matcher.equals(((CachingMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public CachingMatcher(ElementMatcher<? super T> elementMatcher, ConcurrentMap<? super T, Boolean> concurrentMap) {
        this.matcher = elementMatcher;
        this.map = concurrentMap;
    }

    public boolean matches(T t) {
        Boolean bool = (Boolean) this.map.get(t);
        if (bool == null) {
            bool = Boolean.valueOf(onCacheMiss(t));
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: protected */
    public boolean onCacheMiss(T t) {
        boolean matches = this.matcher.matches(t);
        this.map.put(t, Boolean.valueOf(matches));
        return matches;
    }

    public String toString() {
        return "cached(" + this.matcher + ")";
    }

    public static class WithInlineEviction<S> extends CachingMatcher<S> {
        private final int evictionSize;

        public WithInlineEviction(ElementMatcher<? super S> elementMatcher, ConcurrentMap<? super S, Boolean> concurrentMap, int i) {
            super(elementMatcher, concurrentMap);
            this.evictionSize = i;
        }

        /* access modifiers changed from: protected */
        public boolean onCacheMiss(S s) {
            if (this.map.size() >= this.evictionSize) {
                Iterator it = this.map.entrySet().iterator();
                it.next();
                it.remove();
            }
            return CachingMatcher.super.onCacheMiss(s);
        }
    }
}
