package org.simpleframework.xml.stream;

class InputElement implements InputNode {
    private final InputNodeMap map;
    private final EventNode node;
    private final InputNode parent;
    private final NodeReader reader;

    public boolean isElement() {
        return true;
    }

    public InputElement(InputNode inputNode, NodeReader nodeReader, EventNode eventNode) {
        this.map = new InputNodeMap(this, eventNode);
        this.reader = nodeReader;
        this.parent = inputNode;
        this.node = eventNode;
    }

    public Object getSource() {
        return this.node.getSource();
    }

    public InputNode getParent() {
        return this.parent;
    }

    public Position getPosition() {
        return new InputPosition(this.node);
    }

    public String getName() {
        return this.node.getName();
    }

    public String getPrefix() {
        return this.node.getPrefix();
    }

    public String getReference() {
        return this.node.getReference();
    }

    public boolean isRoot() {
        return this.reader.isRoot(this);
    }

    public InputNode getAttribute(String str) {
        return this.map.get(str);
    }

    public NodeMap<InputNode> getAttributes() {
        return this.map;
    }

    public String getValue() throws Exception {
        return this.reader.readValue(this);
    }

    public InputNode getNext() throws Exception {
        return this.reader.readElement(this);
    }

    public InputNode getNext(String str) throws Exception {
        return this.reader.readElement(this, str);
    }

    public void skip() throws Exception {
        this.reader.skipElement(this);
    }

    public boolean isEmpty() throws Exception {
        if (!this.map.isEmpty()) {
            return false;
        }
        return this.reader.isEmpty(this);
    }

    public String toString() {
        return String.format("element %s", new Object[]{getName()});
    }
}
