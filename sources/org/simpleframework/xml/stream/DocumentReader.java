package org.simpleframework.xml.stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

class DocumentReader implements EventReader {
    private static final String RESERVED = "xml";
    private EventNode peek;
    private NodeExtractor queue;
    private NodeStack stack;

    public DocumentReader(Document document) {
        this.queue = new NodeExtractor(document);
        NodeStack nodeStack = new NodeStack();
        this.stack = nodeStack;
        nodeStack.push(document);
    }

    public EventNode peek() throws Exception {
        if (this.peek == null) {
            this.peek = next();
        }
        return this.peek;
    }

    public EventNode next() throws Exception {
        EventNode eventNode = this.peek;
        if (eventNode == null) {
            return read();
        }
        this.peek = null;
        return eventNode;
    }

    private EventNode read() throws Exception {
        Node node = (Node) this.queue.peek();
        if (node == null) {
            return end();
        }
        return read(node);
    }

    private EventNode read(Node node) throws Exception {
        Node parentNode = node.getParentNode();
        Node node2 = (Node) this.stack.top();
        if (parentNode != node2) {
            if (node2 != null) {
                this.stack.pop();
            }
            return end();
        }
        if (node != null) {
            this.queue.poll();
        }
        return convert(node);
    }

    private EventNode convert(Node node) throws Exception {
        if (node.getNodeType() != 1) {
            return text(node);
        }
        if (node != null) {
            this.stack.push(node);
        }
        return start(node);
    }

    private Start start(Node node) {
        Start start = new Start(node);
        return start.isEmpty() ? build(start) : start;
    }

    private Start build(Start start) {
        NamedNodeMap attributes = start.getAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            Entry attribute = attribute(attributes.item(i));
            if (!attribute.isReserved()) {
                start.add(attribute);
            }
        }
        return start;
    }

    private Entry attribute(Node node) {
        return new Entry(node);
    }

    private Text text(Node node) {
        return new Text(node);
    }

    private End end() {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final Node node;

        public Entry(Node node2) {
            this.node = node2;
        }

        public String getName() {
            return this.node.getLocalName();
        }

        public String getValue() {
            return this.node.getNodeValue();
        }

        public String getPrefix() {
            return this.node.getPrefix();
        }

        public String getReference() {
            return this.node.getNamespaceURI();
        }

        public boolean isReserved() {
            String prefix = getPrefix();
            String name = getName();
            if (prefix != null) {
                return prefix.startsWith("xml");
            }
            return name.startsWith("xml");
        }

        public Object getSource() {
            return this.node;
        }
    }

    private static class Start extends EventElement {
        private final Element element;

        public Start(Node node) {
            this.element = (Element) node;
        }

        public String getName() {
            return this.element.getLocalName();
        }

        public String getPrefix() {
            return this.element.getPrefix();
        }

        public String getReference() {
            return this.element.getNamespaceURI();
        }

        public NamedNodeMap getAttributes() {
            return this.element.getAttributes();
        }

        public Object getSource() {
            return this.element;
        }
    }

    private static class Text extends EventToken {
        private final Node node;

        public boolean isText() {
            return true;
        }

        public Text(Node node2) {
            this.node = node2;
        }

        public String getValue() {
            return this.node.getNodeValue();
        }

        public Object getSource() {
            return this.node;
        }
    }

    private static class End extends EventToken {
        public boolean isEnd() {
            return true;
        }

        private End() {
        }
    }
}
