package org.dom4j.rule.pattern;

import org.dom4j.Node;
import org.dom4j.rule.Pattern;

public class NodeTypePattern implements Pattern {
    public static final NodeTypePattern ANY_ATTRIBUTE = new NodeTypePattern(2);
    public static final NodeTypePattern ANY_COMMENT = new NodeTypePattern(8);
    public static final NodeTypePattern ANY_DOCUMENT = new NodeTypePattern(9);
    public static final NodeTypePattern ANY_ELEMENT = new NodeTypePattern(1);
    public static final NodeTypePattern ANY_PROCESSING_INSTRUCTION = new NodeTypePattern(7);
    public static final NodeTypePattern ANY_TEXT = new NodeTypePattern(3);
    private short nodeType;

    public String getMatchesNodeName() {
        return null;
    }

    public double getPriority() {
        return 0.5d;
    }

    public Pattern[] getUnionPatterns() {
        return null;
    }

    public NodeTypePattern(short s) {
        this.nodeType = s;
    }

    public boolean matches(Node node) {
        return node.getNodeType() == this.nodeType;
    }

    public short getMatchType() {
        return this.nodeType;
    }
}
