package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

public class EqualsWithDelta implements ArgumentMatcher<Number>, Serializable {
    private final Number delta;
    private final Number wanted;

    public EqualsWithDelta(Number number, Number number2) {
        this.wanted = number;
        this.delta = number2;
    }

    public boolean matches(Number number) {
        if ((this.wanted == null) ^ (number == null)) {
            return false;
        }
        Number number2 = this.wanted;
        if (number2 == number) {
            return true;
        }
        if (number2.doubleValue() - this.delta.doubleValue() > number.doubleValue() || number.doubleValue() > this.wanted.doubleValue() + this.delta.doubleValue()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "eq(" + this.wanted + ", " + this.delta + ")";
    }
}
