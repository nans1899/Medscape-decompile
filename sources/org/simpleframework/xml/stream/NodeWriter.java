package org.simpleframework.xml.stream;

import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class NodeWriter {
    private final Set active;
    private final OutputStack stack;
    private final boolean verbose;
    private final Formatter writer;

    public NodeWriter(Writer writer2) {
        this(writer2, new Format());
    }

    public NodeWriter(Writer writer2, Format format) {
        this(writer2, format, false);
    }

    private NodeWriter(Writer writer2, Format format, boolean z) {
        this.writer = new Formatter(writer2, format);
        this.active = new HashSet();
        this.stack = new OutputStack(this.active);
        this.verbose = z;
    }

    public OutputNode writeRoot() throws Exception {
        OutputDocument outputDocument = new OutputDocument(this, this.stack);
        if (this.stack.isEmpty()) {
            this.writer.writeProlog();
        }
        return outputDocument;
    }

    public boolean isRoot(OutputNode outputNode) {
        return this.stack.bottom() == outputNode;
    }

    public boolean isCommitted(OutputNode outputNode) {
        return !this.active.contains(outputNode);
    }

    public void commit(OutputNode outputNode) throws Exception {
        if (this.stack.contains(outputNode)) {
            OutputNode pVar = this.stack.top();
            if (!isCommitted(pVar)) {
                writeStart(pVar);
            }
            while (this.stack.top() != outputNode) {
                writeEnd(this.stack.pop());
            }
            writeEnd(outputNode);
            this.stack.pop();
        }
    }

    public void remove(OutputNode outputNode) throws Exception {
        if (this.stack.top() == outputNode) {
            this.stack.pop();
            return;
        }
        throw new NodeException("Cannot remove node");
    }

    public OutputNode writeElement(OutputNode outputNode, String str) throws Exception {
        if (this.stack.isEmpty()) {
            return writeStart(outputNode, str);
        }
        if (!this.stack.contains(outputNode)) {
            return null;
        }
        OutputNode pVar = this.stack.top();
        if (!isCommitted(pVar)) {
            writeStart(pVar);
        }
        while (this.stack.top() != outputNode) {
            writeEnd(this.stack.pop());
        }
        if (!this.stack.isEmpty()) {
            writeValue(outputNode);
        }
        return writeStart(outputNode, str);
    }

    private OutputNode writeStart(OutputNode outputNode, String str) throws Exception {
        OutputElement outputElement = new OutputElement(outputNode, this, str);
        if (str != null) {
            return this.stack.push(outputElement);
        }
        throw new NodeException("Can not have a null name");
    }

    private void writeStart(OutputNode outputNode) throws Exception {
        writeComment(outputNode);
        writeName(outputNode);
        writeAttributes(outputNode);
        writeNamespaces(outputNode);
    }

    private void writeComment(OutputNode outputNode) throws Exception {
        String comment = outputNode.getComment();
        if (comment != null) {
            this.writer.writeComment(comment);
        }
    }

    private void writeName(OutputNode outputNode) throws Exception {
        String prefix = outputNode.getPrefix(this.verbose);
        String name = outputNode.getName();
        if (name != null) {
            this.writer.writeStart(name, prefix);
        }
    }

    private void writeValue(OutputNode outputNode) throws Exception {
        Mode mode = outputNode.getMode();
        String value = outputNode.getValue();
        if (value != null) {
            Iterator<OutputNode> it = this.stack.iterator();
            while (it.hasNext()) {
                OutputNode next = it.next();
                if (mode != Mode.INHERIT) {
                    break;
                }
                mode = next.getMode();
            }
            this.writer.writeText(value, mode);
        }
        outputNode.setValue((String) null);
    }

    private void writeEnd(OutputNode outputNode) throws Exception {
        String name = outputNode.getName();
        String prefix = outputNode.getPrefix(this.verbose);
        if (outputNode.getValue() != null) {
            writeValue(outputNode);
        }
        if (name != null) {
            this.writer.writeEnd(name, prefix);
            this.writer.flush();
        }
    }

    private void writeAttributes(OutputNode outputNode) throws Exception {
        NodeMap<OutputNode> attributes = outputNode.getAttributes();
        for (String next : attributes) {
            OutputNode outputNode2 = attributes.get(next);
            this.writer.writeAttribute(next, outputNode2.getValue(), outputNode2.getPrefix(this.verbose));
        }
        this.active.remove(outputNode);
    }

    private void writeNamespaces(OutputNode outputNode) throws Exception {
        NamespaceMap namespaces = outputNode.getNamespaces();
        for (String next : namespaces) {
            this.writer.writeNamespace(next, namespaces.getPrefix(next));
        }
    }
}
