package org.jaxen.pattern;

import org.jaxen.Context;

public class NodeTypeTest extends NodeTest {
    public static final NodeTypeTest ATTRIBUTE_TEST = new NodeTypeTest(2);
    public static final NodeTypeTest COMMENT_TEST = new NodeTypeTest(8);
    public static final NodeTypeTest DOCUMENT_TEST = new NodeTypeTest(9);
    public static final NodeTypeTest ELEMENT_TEST = new NodeTypeTest(1);
    public static final NodeTypeTest NAMESPACE_TEST = new NodeTypeTest(13);
    public static final NodeTypeTest PROCESSING_INSTRUCTION_TEST = new NodeTypeTest(7);
    public static final NodeTypeTest TEXT_TEST = new NodeTypeTest(3);
    private short nodeType;

    public double getPriority() {
        return -0.5d;
    }

    public NodeTypeTest(short s) {
        this.nodeType = s;
    }

    public boolean matches(Object obj, Context context) {
        return this.nodeType == context.getNavigator().getNodeType(obj);
    }

    public short getMatchType() {
        return this.nodeType;
    }

    public String getText() {
        short s = this.nodeType;
        if (s == 1) {
            return "child()";
        }
        if (s == 2) {
            return "@*";
        }
        if (s == 3) {
            return "text()";
        }
        if (s == 7) {
            return "processing-instruction()";
        }
        if (s == 8) {
            return "comment()";
        }
        if (s != 9) {
            return s != 13 ? "" : "namespace()";
        }
        return "/";
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append("[ type: ");
        stringBuffer.append(this.nodeType);
        stringBuffer.append(" ]");
        return stringBuffer.toString();
    }
}
