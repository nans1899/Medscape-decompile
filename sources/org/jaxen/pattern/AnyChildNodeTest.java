package org.jaxen.pattern;

import org.jaxen.Context;

public class AnyChildNodeTest extends NodeTest {
    private static AnyChildNodeTest instance = new AnyChildNodeTest();

    public short getMatchType() {
        return 0;
    }

    public double getPriority() {
        return -0.5d;
    }

    public String getText() {
        return "*";
    }

    public static AnyChildNodeTest getInstance() {
        return instance;
    }

    public boolean matches(Object obj, Context context) {
        short nodeType = context.getNavigator().getNodeType(obj);
        return nodeType == 1 || nodeType == 3 || nodeType == 8 || nodeType == 7;
    }
}
