package org.simpleframework.xml.stream;

class OutputDocument implements OutputNode {
    private String comment;
    private Mode mode = Mode.INHERIT;
    private String name;
    private String reference;
    private OutputStack stack;
    private OutputNodeMap table = new OutputNodeMap(this);
    private String value;
    private NodeWriter writer;

    public String getName() {
        return null;
    }

    public NamespaceMap getNamespaces() {
        return null;
    }

    public OutputNode getParent() {
        return null;
    }

    public String getPrefix() {
        return null;
    }

    public String getPrefix(boolean z) {
        return null;
    }

    public boolean isRoot() {
        return true;
    }

    public OutputDocument(NodeWriter nodeWriter, OutputStack outputStack) {
        this.writer = nodeWriter;
        this.stack = outputStack;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String str) {
        this.reference = str;
    }

    public String getValue() throws Exception {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode mode2) {
        this.mode = mode2;
    }

    public OutputNode setAttribute(String str, String str2) {
        return this.table.put(str, str2);
    }

    public NodeMap<OutputNode> getAttributes() {
        return this.table;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setData(boolean z) {
        if (z) {
            this.mode = Mode.DATA;
        } else {
            this.mode = Mode.ESCAPE;
        }
    }

    public OutputNode getChild(String str) throws Exception {
        return this.writer.writeElement(this, str);
    }

    public void remove() throws Exception {
        if (!this.stack.isEmpty()) {
            this.stack.bottom().remove();
            return;
        }
        throw new NodeException("No root node");
    }

    public void commit() throws Exception {
        if (!this.stack.isEmpty()) {
            this.stack.bottom().commit();
            return;
        }
        throw new NodeException("No root node");
    }

    public boolean isCommitted() {
        return this.stack.isEmpty();
    }
}
