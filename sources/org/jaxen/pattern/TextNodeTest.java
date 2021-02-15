package org.jaxen.pattern;

import org.jaxen.Context;

public class TextNodeTest extends NodeTest {
    public static final TextNodeTest SINGLETON = new TextNodeTest();

    public short getMatchType() {
        return 3;
    }

    public double getPriority() {
        return -0.5d;
    }

    public String getText() {
        return "text()";
    }

    public boolean matches(Object obj, Context context) {
        return context.getNavigator().isText(obj);
    }
}
