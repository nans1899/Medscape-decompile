package org.simpleframework.xml.stream;

import org.w3c.dom.Node;

class NodeStack extends Stack<Node> {
    public NodeStack() {
        super(6);
    }
}
