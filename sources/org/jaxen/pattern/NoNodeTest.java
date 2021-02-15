package org.jaxen.pattern;

import org.jaxen.Context;

public class NoNodeTest extends NodeTest {
    private static NoNodeTest instance = new NoNodeTest();

    public short getMatchType() {
        return 14;
    }

    public double getPriority() {
        return -0.5d;
    }

    public String getText() {
        return "";
    }

    public boolean matches(Object obj, Context context) {
        return false;
    }

    public static NoNodeTest getInstance() {
        return instance;
    }
}
