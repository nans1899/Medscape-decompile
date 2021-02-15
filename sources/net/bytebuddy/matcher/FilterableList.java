package net.bytebuddy.matcher;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.matcher.FilterableList;

public interface FilterableList<T, S extends FilterableList<T, S>> extends List<T> {
    S filter(ElementMatcher<? super T> elementMatcher);

    T getOnly();

    S subList(int i, int i2);

    public static class Empty<T, S extends FilterableList<T, S>> extends AbstractList<T> implements FilterableList<T, S> {
        public S filter(ElementMatcher<? super T> elementMatcher) {
            return this;
        }

        public int size() {
            return 0;
        }

        public T get(int i) {
            throw new IndexOutOfBoundsException("index = " + i);
        }

        public T getOnly() {
            throw new IllegalStateException("size = 0");
        }

        public S subList(int i, int i2) {
            if (i == i2 && i2 == 0) {
                return this;
            }
            if (i > i2) {
                throw new IllegalArgumentException("fromIndex(" + i + ") > toIndex(" + i2 + ")");
            }
            throw new IndexOutOfBoundsException("fromIndex = " + i);
        }
    }

    public static abstract class AbstractBase<T, S extends FilterableList<T, S>> extends AbstractList<T> implements FilterableList<T, S> {
        private static final int ONLY = 0;

        /* access modifiers changed from: protected */
        public abstract S wrap(List<T> list);

        public S filter(ElementMatcher<? super T> elementMatcher) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (elementMatcher.matches(next)) {
                    arrayList.add(next);
                }
            }
            if (arrayList.size() == size()) {
                return this;
            }
            return wrap(arrayList);
        }

        public T getOnly() {
            if (size() == 1) {
                return get(0);
            }
            throw new IllegalStateException("size = " + size());
        }

        public S subList(int i, int i2) {
            return wrap(super.subList(i, i2));
        }
    }
}
