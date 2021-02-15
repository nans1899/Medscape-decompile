package org.jaxen.dom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenConstants;
import org.jaxen.Navigator;
import org.jaxen.XPath;
import org.jaxen.saxpath.SAXPathException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.SAXException;

public class DocumentNavigator extends DefaultNavigator {
    private static final DocumentNavigator SINGLETON = new DocumentNavigator();
    private static final long serialVersionUID = 8460943068889528115L;

    public static Navigator getInstance() {
        return SINGLETON;
    }

    public Iterator getChildAxisIterator(Object obj) {
        return new NodeIterator(this, (Node) obj) {
            private final /* synthetic */ DocumentNavigator this$0;

            /* access modifiers changed from: protected */
            public Node getFirstNode(Node node) {
                return node.getFirstChild();
            }

            {
                this.this$0 = r1;
            }

            /* access modifiers changed from: protected */
            public Node getNextNode(Node node) {
                return node.getNextSibling();
            }
        };
    }

    public Iterator getParentAxisIterator(Object obj) {
        Node node = (Node) obj;
        return node.getNodeType() == 2 ? new NodeIterator(this, node) {
            private final /* synthetic */ DocumentNavigator this$0;

            /* access modifiers changed from: protected */
            public Node getNextNode(Node node) {
                return null;
            }

            /* access modifiers changed from: protected */
            public Node getFirstNode(Node node) {
                return ((Attr) node).getOwnerElement();
            }

            {
                this.this$0 = r1;
            }
        } : new NodeIterator(this, node) {
            private final /* synthetic */ DocumentNavigator this$0;

            /* access modifiers changed from: protected */
            public Node getNextNode(Node node) {
                return null;
            }

            /* access modifiers changed from: protected */
            public Node getFirstNode(Node node) {
                return node.getParentNode();
            }

            {
                this.this$0 = r1;
            }
        };
    }

    public Object getParentNode(Object obj) {
        Node node = (Node) obj;
        if (node.getNodeType() == 2) {
            return ((Attr) node).getOwnerElement();
        }
        return node.getParentNode();
    }

    public Iterator getFollowingSiblingAxisIterator(Object obj) {
        return new NodeIterator(this, (Node) obj) {
            private final /* synthetic */ DocumentNavigator this$0;

            /* access modifiers changed from: protected */
            public Node getFirstNode(Node node) {
                return getNextNode(node);
            }

            {
                this.this$0 = r1;
            }

            /* access modifiers changed from: protected */
            public Node getNextNode(Node node) {
                return node.getNextSibling();
            }
        };
    }

    public Iterator getPrecedingSiblingAxisIterator(Object obj) {
        return new NodeIterator(this, (Node) obj) {
            private final /* synthetic */ DocumentNavigator this$0;

            /* access modifiers changed from: protected */
            public Node getFirstNode(Node node) {
                return getNextNode(node);
            }

            {
                this.this$0 = r1;
            }

            /* access modifiers changed from: protected */
            public Node getNextNode(Node node) {
                return node.getPreviousSibling();
            }
        };
    }

    public Iterator getFollowingAxisIterator(Object obj) {
        return new NodeIterator(this, (Node) obj) {
            private final /* synthetic */ DocumentNavigator this$0;

            /* access modifiers changed from: protected */
            public Node getFirstNode(Node node) {
                if (node == null) {
                    return null;
                }
                Node nextSibling = node.getNextSibling();
                return nextSibling == null ? getFirstNode(node.getParentNode()) : nextSibling;
            }

            {
                this.this$0 = r1;
            }

            /* access modifiers changed from: protected */
            public Node getNextNode(Node node) {
                if (node == null) {
                    return null;
                }
                Node firstChild = node.getFirstChild();
                if (firstChild == null) {
                    firstChild = node.getNextSibling();
                }
                return firstChild == null ? getFirstNode(node.getParentNode()) : firstChild;
            }
        };
    }

    public Iterator getAttributeAxisIterator(Object obj) {
        if (isElement(obj)) {
            return new AttributeIterator((Node) obj);
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getNamespaceAxisIterator(Object obj) {
        if (!isElement(obj)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        HashMap hashMap = new HashMap();
        Node node = (Node) obj;
        for (Node node2 = node; node2 != null; node2 = node2.getParentNode()) {
            String namespaceURI = node2.getNamespaceURI();
            if (namespaceURI != null && !"".equals(namespaceURI)) {
                String prefix = node2.getPrefix();
                if (!hashMap.containsKey(prefix)) {
                    hashMap.put(prefix, new NamespaceNode(node, prefix, namespaceURI));
                }
            }
            if (node2.hasAttributes()) {
                NamedNodeMap attributes = node2.getAttributes();
                int length = attributes.getLength();
                for (int i = 0; i < length; i++) {
                    Attr attr = (Attr) attributes.item(i);
                    String namespaceURI2 = attr.getNamespaceURI();
                    if (!XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI2) && namespaceURI2 != null) {
                        String prefix2 = attr.getPrefix();
                        NamespaceNode namespaceNode = new NamespaceNode(node, prefix2, namespaceURI2);
                        if (!hashMap.containsKey(prefix2)) {
                            hashMap.put(prefix2, namespaceNode);
                        }
                    }
                }
                for (int i2 = 0; i2 < length; i2++) {
                    Attr attr2 = (Attr) attributes.item(i2);
                    if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(attr2.getNamespaceURI())) {
                        NamespaceNode namespaceNode2 = new NamespaceNode(node, attr2);
                        String nodeName = namespaceNode2.getNodeName();
                        if (!hashMap.containsKey(nodeName)) {
                            hashMap.put(nodeName, namespaceNode2);
                        }
                    }
                }
            }
        }
        hashMap.put("xml", new NamespaceNode(node, "xml", XMLConstants.XML_NS_URI));
        NamespaceNode namespaceNode3 = (NamespaceNode) hashMap.get("");
        if (namespaceNode3 != null && namespaceNode3.getNodeValue().length() == 0) {
            hashMap.remove("");
        }
        return hashMap.values().iterator();
    }

    public XPath parseXPath(String str) throws SAXPathException {
        return new DOMXPath(str);
    }

    public Object getDocumentNode(Object obj) {
        if (isDocument(obj)) {
            return obj;
        }
        return ((Node) obj).getOwnerDocument();
    }

    public String getElementNamespaceUri(Object obj) {
        try {
            Node node = (Node) obj;
            if (node.getNodeType() == 1) {
                return node.getNamespaceURI();
            }
            return null;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public String getElementName(Object obj) {
        if (!isElement(obj)) {
            return null;
        }
        Node node = (Node) obj;
        String localName = node.getLocalName();
        return localName == null ? node.getNodeName() : localName;
    }

    public String getElementQName(Object obj) {
        try {
            Node node = (Node) obj;
            if (node.getNodeType() == 1) {
                return node.getNodeName();
            }
            return null;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public String getAttributeNamespaceUri(Object obj) {
        try {
            Node node = (Node) obj;
            if (node.getNodeType() == 2) {
                return node.getNamespaceURI();
            }
            return null;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public String getAttributeName(Object obj) {
        if (!isAttribute(obj)) {
            return null;
        }
        Node node = (Node) obj;
        String localName = node.getLocalName();
        return localName == null ? node.getNodeName() : localName;
    }

    public String getAttributeQName(Object obj) {
        try {
            Node node = (Node) obj;
            if (node.getNodeType() == 2) {
                return node.getNodeName();
            }
            return null;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public boolean isDocument(Object obj) {
        return (obj instanceof Node) && ((Node) obj).getNodeType() == 9;
    }

    public boolean isNamespace(Object obj) {
        return obj instanceof NamespaceNode;
    }

    public boolean isElement(Object obj) {
        return (obj instanceof Node) && ((Node) obj).getNodeType() == 1;
    }

    public boolean isAttribute(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return node.getNodeType() == 2 && !XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(node.getNamespaceURI());
        }
    }

    public boolean isComment(Object obj) {
        return (obj instanceof Node) && ((Node) obj).getNodeType() == 8;
    }

    public boolean isText(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        short nodeType = ((Node) obj).getNodeType();
        if (nodeType == 3 || nodeType == 4) {
            return true;
        }
        return false;
    }

    public boolean isProcessingInstruction(Object obj) {
        return (obj instanceof Node) && ((Node) obj).getNodeType() == 7;
    }

    public String getElementStringValue(Object obj) {
        if (isElement(obj)) {
            return getStringValue((Node) obj, new StringBuffer()).toString();
        }
        return null;
    }

    private StringBuffer getStringValue(Node node, StringBuffer stringBuffer) {
        if (isText(node)) {
            stringBuffer.append(node.getNodeValue());
        } else {
            NodeList childNodes = node.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                getStringValue(childNodes.item(i), stringBuffer);
            }
        }
        return stringBuffer;
    }

    public String getAttributeStringValue(Object obj) {
        if (isAttribute(obj)) {
            return ((Node) obj).getNodeValue();
        }
        return null;
    }

    public String getTextStringValue(Object obj) {
        if (isText(obj)) {
            return ((Node) obj).getNodeValue();
        }
        return null;
    }

    public String getCommentStringValue(Object obj) {
        if (isComment(obj)) {
            return ((Node) obj).getNodeValue();
        }
        return null;
    }

    public String getNamespaceStringValue(Object obj) {
        if (isNamespace(obj)) {
            return ((NamespaceNode) obj).getNodeValue();
        }
        return null;
    }

    public String getNamespacePrefix(Object obj) {
        if (isNamespace(obj)) {
            return ((NamespaceNode) obj).getLocalName();
        }
        return null;
    }

    public String translateNamespacePrefixToUri(String str, Object obj) {
        Iterator namespaceAxisIterator = getNamespaceAxisIterator(obj);
        while (namespaceAxisIterator.hasNext()) {
            NamespaceNode namespaceNode = (NamespaceNode) namespaceAxisIterator.next();
            if (str.equals(namespaceNode.getNodeName())) {
                return namespaceNode.getNodeValue();
            }
        }
        return null;
    }

    public Object getDocument(String str) throws FunctionCallException {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            newInstance.setNamespaceAware(true);
            return newInstance.newDocumentBuilder().parse(str);
        } catch (ParserConfigurationException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("JAXP setup error in document() function: ");
            stringBuffer.append(e.getMessage());
            throw new FunctionCallException(stringBuffer.toString(), e);
        } catch (SAXException e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("XML error in document() function: ");
            stringBuffer2.append(e2.getMessage());
            throw new FunctionCallException(stringBuffer2.toString(), e2);
        } catch (IOException e3) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("I/O error in document() function: ");
            stringBuffer3.append(e3.getMessage());
            throw new FunctionCallException(stringBuffer3.toString(), e3);
        }
    }

    public String getProcessingInstructionTarget(Object obj) {
        if (isProcessingInstruction(obj)) {
            return ((ProcessingInstruction) obj).getTarget();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj);
        stringBuffer.append(" is not a processing instruction");
        throw new ClassCastException(stringBuffer.toString());
    }

    public String getProcessingInstructionData(Object obj) {
        if (isProcessingInstruction(obj)) {
            return ((ProcessingInstruction) obj).getData();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(obj);
        stringBuffer.append(" is not a processing instruction");
        throw new ClassCastException(stringBuffer.toString());
    }

    abstract class NodeIterator implements Iterator {
        private Node node;

        /* access modifiers changed from: protected */
        public abstract Node getFirstNode(Node node2);

        /* access modifiers changed from: protected */
        public abstract Node getNextNode(Node node2);

        public NodeIterator(Node node2) {
            this.node = getFirstNode(node2);
            while (!isXPathNode(this.node)) {
                this.node = getNextNode(this.node);
            }
        }

        public boolean hasNext() {
            return this.node != null;
        }

        public Object next() {
            Node node2 = this.node;
            if (node2 != null) {
                this.node = getNextNode(node2);
                while (!isXPathNode(this.node)) {
                    this.node = getNextNode(this.node);
                }
                return node2;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private boolean isXPathNode(Node node2) {
            if (node2 == null) {
                return true;
            }
            short nodeType = node2.getNodeType();
            if (nodeType == 5 || nodeType == 6) {
                return false;
            }
            switch (nodeType) {
                case 10:
                case 11:
                case 12:
                    return false;
                default:
                    return true;
            }
        }
    }

    private static class AttributeIterator implements Iterator {
        private int lastAttribute = -1;
        private NamedNodeMap map;
        private int pos;

        AttributeIterator(Node node) {
            NamedNodeMap attributes = node.getAttributes();
            this.map = attributes;
            this.pos = 0;
            for (int length = attributes.getLength() - 1; length >= 0; length--) {
                if (!XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(this.map.item(length).getNamespaceURI())) {
                    this.lastAttribute = length;
                    return;
                }
            }
        }

        public boolean hasNext() {
            return this.pos <= this.lastAttribute;
        }

        public Object next() {
            NamedNodeMap namedNodeMap = this.map;
            int i = this.pos;
            this.pos = i + 1;
            Node item = namedNodeMap.item(i);
            if (item != null) {
                return XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(item.getNamespaceURI()) ? next() : item;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Object getElementById(Object obj, String str) {
        Document document = (Document) getDocumentNode(obj);
        if (document != null) {
            return document.getElementById(str);
        }
        return null;
    }
}
