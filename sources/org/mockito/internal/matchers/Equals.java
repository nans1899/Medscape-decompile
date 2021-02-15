package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.text.ValuePrinter;

public class Equals implements ArgumentMatcher<Object>, ContainsExtraTypeInfo, Serializable {
    private final Object wanted;

    public int hashCode() {
        return 1;
    }

    public Equals(Object obj) {
        this.wanted = obj;
    }

    public boolean matches(Object obj) {
        return Equality.areEqual(this.wanted, obj);
    }

    public String toString() {
        return describe(this.wanted);
    }

    private String describe(Object obj) {
        return ValuePrinter.print(obj);
    }

    /* access modifiers changed from: protected */
    public final Object getWanted() {
        return this.wanted;
    }

    public boolean equals(Object obj) {
        Object obj2;
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        Equals equals = (Equals) obj;
        if ((this.wanted != null || equals.wanted != null) && ((obj2 = this.wanted) == null || !obj2.equals(equals.wanted))) {
            return false;
        }
        return true;
    }

    public String toStringWithType() {
        return "(" + this.wanted.getClass().getSimpleName() + ") " + describe(this.wanted);
    }

    public boolean typeMatches(Object obj) {
        return (this.wanted == null || obj == null || obj.getClass() != this.wanted.getClass()) ? false : true;
    }
}
