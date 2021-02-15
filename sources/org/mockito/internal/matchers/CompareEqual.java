package org.mockito.internal.matchers;

import java.io.Serializable;
import java.lang.Comparable;

public class CompareEqual<T extends Comparable<T>> extends CompareTo<T> implements Serializable {
    /* access modifiers changed from: protected */
    public String getName() {
        return "cmpEq";
    }

    /* access modifiers changed from: protected */
    public boolean matchResult(int i) {
        return i == 0;
    }

    public CompareEqual(T t) {
        super(t);
    }
}
