package org.jaxen.pattern;

import org.jaxen.Context;

public class AnyNodeTest extends NodeTest {
    private static AnyNodeTest instance = new AnyNodeTest();

    public short getMatchType() {
        return 0;
    }

    public double getPriority() {
        return -0.5d;
    }

    public String getText() {
        return "*";
    }

    public boolean matches(Object obj, Context context) {
        return true;
    }

    public static AnyNodeTest getInstance() {
        return instance;
    }

    private AnyNodeTest() {
    }
}
