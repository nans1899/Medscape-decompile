package org.mozilla.javascript.xmlimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import org.mozilla.javascript.Undefined;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.SAXException;

class XmlNode implements Serializable {
    private static final boolean DOM_LEVEL_3 = true;
    private static final String USER_DATA_XMLNODE_KEY = XmlNode.class.getName();
    private static final String XML_NAMESPACES_NAMESPACE_URI = "http://www.w3.org/2000/xmlns/";
    private static final long serialVersionUID = 1;
    private Node dom;
    private UserDataHandler events = new XmlNodeUserDataHandler();
    private XML xml;

    private String toUri(String str) {
        return str == null ? "" : str;
    }

    private static XmlNode getUserData(Node node) {
        return (XmlNode) node.getUserData(USER_DATA_XMLNODE_KEY);
    }

    private static void setUserData(Node node, XmlNode xmlNode) {
        node.setUserData(USER_DATA_XMLNODE_KEY, xmlNode, xmlNode.events);
    }

    private static XmlNode createImpl(Node node) {
        if (node instanceof Document) {
            throw new IllegalArgumentException();
        } else if (getUserData(node) != null) {
            return getUserData(node);
        } else {
            XmlNode xmlNode = new XmlNode();
            xmlNode.dom = node;
            setUserData(node, xmlNode);
            return xmlNode;
        }
    }

    static XmlNode newElementWithText(XmlProcessor xmlProcessor, XmlNode xmlNode, QName qName, String str) {
        Document document;
        String str2;
        if (!(xmlNode instanceof Document)) {
            if (xmlNode != null) {
                document = xmlNode.dom.getOwnerDocument();
            } else {
                document = xmlProcessor.newDocument();
            }
            String str3 = null;
            Node node = xmlNode != null ? xmlNode.dom : null;
            Namespace namespace = qName.getNamespace();
            if (namespace == null || namespace.getUri().length() == 0) {
                str2 = qName.getLocalName();
            } else {
                str3 = namespace.getUri();
                str2 = qName.qualify(node);
            }
            Element createElementNS = document.createElementNS(str3, str2);
            if (str != null) {
                createElementNS.appendChild(document.createTextNode(str));
            }
            return createImpl(createElementNS);
        }
        throw new IllegalArgumentException("Cannot use Document node as reference");
    }

    static XmlNode createText(XmlProcessor xmlProcessor, String str) {
        return createImpl(xmlProcessor.newDocument().createTextNode(str));
    }

    static XmlNode createElementFromNode(Node node) {
        if (node instanceof Document) {
            node = ((Document) node).getDocumentElement();
        }
        return createImpl(node);
    }

    static XmlNode createElement(XmlProcessor xmlProcessor, String str, String str2) throws SAXException {
        return createImpl(xmlProcessor.toXml(str, str2));
    }

    static XmlNode createEmpty(XmlProcessor xmlProcessor) {
        return createText(xmlProcessor, "");
    }

    private static XmlNode copy(XmlNode xmlNode) {
        return createImpl(xmlNode.dom.cloneNode(true));
    }

    private XmlNode() {
    }

    /* access modifiers changed from: package-private */
    public String debug() {
        XmlProcessor xmlProcessor = new XmlProcessor();
        xmlProcessor.setIgnoreComments(false);
        xmlProcessor.setIgnoreProcessingInstructions(false);
        xmlProcessor.setIgnoreWhitespace(false);
        xmlProcessor.setPrettyPrinting(false);
        return xmlProcessor.ecmaToXmlString(this.dom);
    }

    public String toString() {
        return "XmlNode: type=" + this.dom.getNodeType() + " dom=" + this.dom.toString();
    }

    /* access modifiers changed from: package-private */
    public XML getXml() {
        return this.xml;
    }

    /* access modifiers changed from: package-private */
    public void setXml(XML xml2) {
        this.xml = xml2;
    }

    /* access modifiers changed from: package-private */
    public int getChildCount() {
        return this.dom.getChildNodes().getLength();
    }

    /* access modifiers changed from: package-private */
    public XmlNode parent() {
        Node parentNode = this.dom.getParentNode();
        if (!(parentNode instanceof Document) && parentNode != null) {
            return createImpl(parentNode);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getChildIndex() {
        if (isAttributeType() || parent() == null) {
            return -1;
        }
        NodeList childNodes = this.dom.getParentNode().getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) == this.dom) {
                return i;
            }
        }
        throw new RuntimeException("Unreachable.");
    }

    /* access modifiers changed from: package-private */
    public void removeChild(int i) {
        Node node = this.dom;
        node.removeChild(node.getChildNodes().item(i));
    }

    /* access modifiers changed from: package-private */
    public String toXmlString(XmlProcessor xmlProcessor) {
        return xmlProcessor.ecmaToXmlString(this.dom);
    }

    /* access modifiers changed from: package-private */
    public String ecmaValue() {
        if (isTextType()) {
            return ((Text) this.dom).getData();
        }
        if (isAttributeType()) {
            return ((Attr) this.dom).getValue();
        }
        if (isProcessingInstructionType()) {
            return ((ProcessingInstruction) this.dom).getData();
        }
        if (isCommentType()) {
            return ((Comment) this.dom).getNodeValue();
        }
        if (isElementType()) {
            throw new RuntimeException("Unimplemented ecmaValue() for elements.");
        }
        throw new RuntimeException("Unimplemented for node " + this.dom);
    }

    /* access modifiers changed from: package-private */
    public void deleteMe() {
        Node node = this.dom;
        if (node instanceof Attr) {
            Attr attr = (Attr) node;
            attr.getOwnerElement().getAttributes().removeNamedItemNS(attr.getNamespaceURI(), attr.getLocalName());
        } else if (node.getParentNode() != null) {
            this.dom.getParentNode().removeChild(this.dom);
        }
    }

    /* access modifiers changed from: package-private */
    public void normalize() {
        this.dom.normalize();
    }

    /* access modifiers changed from: package-private */
    public void insertChildAt(int i, XmlNode xmlNode) {
        Node node = this.dom;
        Node importNode = node.getOwnerDocument().importNode(xmlNode.dom, true);
        if (node.getChildNodes().getLength() < i) {
            throw new IllegalArgumentException("index=" + i + " length=" + node.getChildNodes().getLength());
        } else if (node.getChildNodes().getLength() == i) {
            node.appendChild(importNode);
        } else {
            node.insertBefore(importNode, node.getChildNodes().item(i));
        }
    }

    /* access modifiers changed from: package-private */
    public void insertChildrenAt(int i, XmlNode[] xmlNodeArr) {
        for (int i2 = 0; i2 < xmlNodeArr.length; i2++) {
            insertChildAt(i + i2, xmlNodeArr[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public XmlNode getChild(int i) {
        return createImpl(this.dom.getChildNodes().item(i));
    }

    /* access modifiers changed from: package-private */
    public boolean hasChildElement() {
        NodeList childNodes = this.dom.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == 1) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isSameNode(XmlNode xmlNode) {
        return this.dom == xmlNode.dom;
    }

    private void addNamespaces(Namespaces namespaces, Element element) {
        String str;
        if (element != null) {
            String uri = toUri(element.lookupNamespaceURI((String) null));
            if (element.getParentNode() != null) {
                str = toUri(element.getParentNode().lookupNamespaceURI((String) null));
            } else {
                str = "";
            }
            if (!uri.equals(str) || !(element.getParentNode() instanceof Element)) {
                namespaces.declare(Namespace.create("", uri));
            }
            NamedNodeMap attributes = element.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attr = (Attr) attributes.item(i);
                if (attr.getPrefix() != null && attr.getPrefix().equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                    namespaces.declare(Namespace.create(attr.getLocalName(), attr.getValue()));
                }
            }
            return;
        }
        throw new RuntimeException("element must not be null");
    }

    private Namespaces getAllNamespaces() {
        Namespaces namespaces = new Namespaces();
        Node node = this.dom;
        if (node instanceof Attr) {
            node = ((Attr) node).getOwnerElement();
        }
        while (node != null) {
            if (node instanceof Element) {
                addNamespaces(namespaces, (Element) node);
            }
            node = node.getParentNode();
        }
        namespaces.declare(Namespace.create("", ""));
        return namespaces;
    }

    /* access modifiers changed from: package-private */
    public Namespace[] getInScopeNamespaces() {
        return getAllNamespaces().getNamespaces();
    }

    /* access modifiers changed from: package-private */
    public Namespace[] getNamespaceDeclarations() {
        if (!(this.dom instanceof Element)) {
            return new Namespace[0];
        }
        Namespaces namespaces = new Namespaces();
        addNamespaces(namespaces, (Element) this.dom);
        return namespaces.getNamespaces();
    }

    /* access modifiers changed from: package-private */
    public Namespace getNamespaceDeclaration(String str) {
        if (!str.equals("") || !(this.dom instanceof Attr)) {
            return getAllNamespaces().getNamespace(str);
        }
        return Namespace.create("", "");
    }

    /* access modifiers changed from: package-private */
    public Namespace getNamespaceDeclaration() {
        if (this.dom.getPrefix() == null) {
            return getNamespaceDeclaration("");
        }
        return getNamespaceDeclaration(this.dom.getPrefix());
    }

    static class XmlNodeUserDataHandler implements UserDataHandler, Serializable {
        private static final long serialVersionUID = 4666895518900769588L;

        public void handle(short s, String str, Object obj, Node node, Node node2) {
        }

        XmlNodeUserDataHandler() {
        }
    }

    private static class Namespaces {
        private Map<String, String> map = new HashMap();
        private Map<String, String> uriToPrefix = new HashMap();

        Namespaces() {
        }

        /* access modifiers changed from: package-private */
        public void declare(Namespace namespace) {
            if (this.map.get(namespace.prefix) == null) {
                this.map.put(namespace.prefix, namespace.uri);
            }
            if (this.uriToPrefix.get(namespace.uri) == null) {
                this.uriToPrefix.put(namespace.uri, namespace.prefix);
            }
        }

        /* access modifiers changed from: package-private */
        public Namespace getNamespaceByUri(String str) {
            if (this.uriToPrefix.get(str) == null) {
                return null;
            }
            return Namespace.create(str, this.uriToPrefix.get(str));
        }

        /* access modifiers changed from: package-private */
        public Namespace getNamespace(String str) {
            if (this.map.get(str) == null) {
                return null;
            }
            return Namespace.create(str, this.map.get(str));
        }

        /* access modifiers changed from: package-private */
        public Namespace[] getNamespaces() {
            ArrayList arrayList = new ArrayList();
            for (String next : this.map.keySet()) {
                Namespace create = Namespace.create(next, this.map.get(next));
                if (!create.isEmpty()) {
                    arrayList.add(create);
                }
            }
            return (Namespace[]) arrayList.toArray(new Namespace[arrayList.size()]);
        }
    }

    /* access modifiers changed from: package-private */
    public final XmlNode copy() {
        return copy(this);
    }

    /* access modifiers changed from: package-private */
    public final boolean isParentType() {
        return isElementType();
    }

    /* access modifiers changed from: package-private */
    public final boolean isTextType() {
        return this.dom.getNodeType() == 3 || this.dom.getNodeType() == 4;
    }

    /* access modifiers changed from: package-private */
    public final boolean isAttributeType() {
        return this.dom.getNodeType() == 2;
    }

    /* access modifiers changed from: package-private */
    public final boolean isProcessingInstructionType() {
        return this.dom.getNodeType() == 7;
    }

    /* access modifiers changed from: package-private */
    public final boolean isCommentType() {
        return this.dom.getNodeType() == 8;
    }

    /* access modifiers changed from: package-private */
    public final boolean isElementType() {
        return this.dom.getNodeType() == 1;
    }

    /* access modifiers changed from: package-private */
    public final void renameNode(QName qName) {
        this.dom = this.dom.getOwnerDocument().renameNode(this.dom, qName.getNamespace().getUri(), qName.qualify(this.dom));
    }

    /* access modifiers changed from: package-private */
    public void invalidateNamespacePrefix() {
        Node node = this.dom;
        if (node instanceof Element) {
            String prefix = node.getPrefix();
            renameNode(QName.create(this.dom.getNamespaceURI(), this.dom.getLocalName(), (String) null));
            NamedNodeMap attributes = this.dom.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.item(i).getPrefix().equals(prefix)) {
                    createImpl(attributes.item(i)).renameNode(QName.create(attributes.item(i).getNamespaceURI(), attributes.item(i).getLocalName(), (String) null));
                }
            }
            return;
        }
        throw new IllegalStateException();
    }

    private void declareNamespace(Element element, String str, String str2) {
        if (str.length() > 0) {
            element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + str, str2);
            return;
        }
        element.setAttribute(XMLConstants.XMLNS_ATTRIBUTE, str2);
    }

    /* access modifiers changed from: package-private */
    public void declareNamespace(String str, String str2) {
        Node node = this.dom;
        if (!(node instanceof Element)) {
            throw new IllegalStateException();
        } else if (node.lookupNamespaceURI(str2) == null || !this.dom.lookupNamespaceURI(str2).equals(str)) {
            declareNamespace((Element) this.dom, str, str2);
        }
    }

    private Namespace getDefaultNamespace() {
        return Namespace.create("", this.dom.lookupNamespaceURI((String) null) == null ? "" : this.dom.lookupNamespaceURI((String) null));
    }

    private String getExistingPrefixFor(Namespace namespace) {
        if (getDefaultNamespace().getUri().equals(namespace.getUri())) {
            return "";
        }
        return this.dom.lookupPrefix(namespace.getUri());
    }

    private Namespace getNodeNamespace() {
        String namespaceURI = this.dom.getNamespaceURI();
        String prefix = this.dom.getPrefix();
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        if (prefix == null) {
            prefix = "";
        }
        return Namespace.create(prefix, namespaceURI);
    }

    /* access modifiers changed from: package-private */
    public Namespace getNamespace() {
        return getNodeNamespace();
    }

    /* access modifiers changed from: package-private */
    public void removeNamespace(Namespace namespace) {
        if (!namespace.is(getNodeNamespace())) {
            NamedNodeMap attributes = this.dom.getAttributes();
            int i = 0;
            while (i < attributes.getLength()) {
                if (!namespace.is(createImpl(attributes.item(i)).getNodeNamespace())) {
                    i++;
                } else {
                    return;
                }
            }
            String existingPrefixFor = getExistingPrefixFor(namespace);
            if (existingPrefixFor == null) {
                return;
            }
            if (namespace.isUnspecifiedPrefix()) {
                declareNamespace(existingPrefixFor, getDefaultNamespace().getUri());
            } else if (existingPrefixFor.equals(namespace.getPrefix())) {
                declareNamespace(existingPrefixFor, getDefaultNamespace().getUri());
            }
        }
    }

    private void setProcessingInstructionName(String str) {
        ProcessingInstruction processingInstruction = (ProcessingInstruction) this.dom;
        processingInstruction.getParentNode().replaceChild(processingInstruction, processingInstruction.getOwnerDocument().createProcessingInstruction(str, processingInstruction.getData()));
    }

    /* access modifiers changed from: package-private */
    public final void setLocalName(String str) {
        Node node = this.dom;
        if (node instanceof ProcessingInstruction) {
            setProcessingInstructionName(str);
            return;
        }
        String prefix = node.getPrefix();
        if (prefix == null) {
            prefix = "";
        }
        Document ownerDocument = this.dom.getOwnerDocument();
        Node node2 = this.dom;
        this.dom = ownerDocument.renameNode(node2, node2.getNamespaceURI(), QName.qualify(prefix, str));
    }

    /* access modifiers changed from: package-private */
    public final QName getQname() {
        String str = "";
        String namespaceURI = this.dom.getNamespaceURI() == null ? str : this.dom.getNamespaceURI();
        if (this.dom.getPrefix() != null) {
            str = this.dom.getPrefix();
        }
        return QName.create(namespaceURI, this.dom.getLocalName(), str);
    }

    /* access modifiers changed from: package-private */
    public void addMatchingChildren(XMLList xMLList, Filter filter) {
        NodeList childNodes = this.dom.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            XmlNode createImpl = createImpl(item);
            if (filter.accept(item)) {
                xMLList.addToList(createImpl);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public XmlNode[] getMatchingChildren(Filter filter) {
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = this.dom.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (filter.accept(item)) {
                arrayList.add(createImpl(item));
            }
        }
        return (XmlNode[]) arrayList.toArray(new XmlNode[arrayList.size()]);
    }

    /* access modifiers changed from: package-private */
    public XmlNode[] getAttributes() {
        NamedNodeMap attributes = this.dom.getAttributes();
        if (attributes != null) {
            XmlNode[] xmlNodeArr = new XmlNode[attributes.getLength()];
            for (int i = 0; i < attributes.getLength(); i++) {
                xmlNodeArr[i] = createImpl(attributes.item(i));
            }
            return xmlNodeArr;
        }
        throw new IllegalStateException("Must be element.");
    }

    /* access modifiers changed from: package-private */
    public String getAttributeValue() {
        return ((Attr) this.dom).getValue();
    }

    /* access modifiers changed from: package-private */
    public void setAttribute(QName qName, String str) {
        Node node = this.dom;
        if (node instanceof Element) {
            qName.setAttribute((Element) node, str);
            return;
        }
        throw new IllegalStateException("Can only set attribute on elements.");
    }

    /* access modifiers changed from: package-private */
    public void replaceWith(XmlNode xmlNode) {
        Node node = xmlNode.dom;
        if (node.getOwnerDocument() != this.dom.getOwnerDocument()) {
            node = this.dom.getOwnerDocument().importNode(node, true);
        }
        this.dom.getParentNode().replaceChild(node, this.dom);
    }

    /* access modifiers changed from: package-private */
    public String ecmaToXMLString(XmlProcessor xmlProcessor) {
        if (!isElementType()) {
            return xmlProcessor.ecmaToXmlString(this.dom);
        }
        Element element = (Element) this.dom.cloneNode(true);
        Namespace[] inScopeNamespaces = getInScopeNamespaces();
        for (int i = 0; i < inScopeNamespaces.length; i++) {
            declareNamespace(element, inScopeNamespaces[i].getPrefix(), inScopeNamespaces[i].getUri());
        }
        return xmlProcessor.ecmaToXmlString(element);
    }

    static class Namespace implements Serializable {
        static final Namespace GLOBAL = create("", "");
        private static final long serialVersionUID = 4073904386884677090L;
        /* access modifiers changed from: private */
        public String prefix;
        /* access modifiers changed from: private */
        public String uri;

        static Namespace create(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("Empty string represents default namespace prefix");
            } else if (str2 != null) {
                Namespace namespace = new Namespace();
                namespace.prefix = str;
                namespace.uri = str2;
                return namespace;
            } else {
                throw new IllegalArgumentException("Namespace may not lack a URI");
            }
        }

        static Namespace create(String str) {
            Namespace namespace = new Namespace();
            namespace.uri = str;
            if (str == null || str.length() == 0) {
                namespace.prefix = "";
            }
            return namespace;
        }

        private Namespace() {
        }

        public String toString() {
            if (this.prefix == null) {
                return "XmlNode.Namespace [" + this.uri + "]";
            }
            return "XmlNode.Namespace [" + this.prefix + "{" + this.uri + "}]";
        }

        /* access modifiers changed from: package-private */
        public boolean isUnspecifiedPrefix() {
            return this.prefix == null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r1 = r3.prefix;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean is(org.mozilla.javascript.xmlimpl.XmlNode.Namespace r3) {
            /*
                r2 = this;
                java.lang.String r0 = r2.prefix
                if (r0 == 0) goto L_0x001a
                java.lang.String r1 = r3.prefix
                if (r1 == 0) goto L_0x001a
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x001a
                java.lang.String r0 = r2.uri
                java.lang.String r3 = r3.uri
                boolean r3 = r0.equals(r3)
                if (r3 == 0) goto L_0x001a
                r3 = 1
                goto L_0x001b
            L_0x001a:
                r3 = 0
            L_0x001b:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XmlNode.Namespace.is(org.mozilla.javascript.xmlimpl.XmlNode$Namespace):boolean");
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            String str = this.prefix;
            return str != null && str.equals("") && this.uri.equals("");
        }

        /* access modifiers changed from: package-private */
        public boolean isDefault() {
            String str = this.prefix;
            return str != null && str.equals("");
        }

        /* access modifiers changed from: package-private */
        public boolean isGlobal() {
            String str = this.uri;
            return str != null && str.equals("");
        }

        /* access modifiers changed from: private */
        public void setPrefix(String str) {
            if (str != null) {
                this.prefix = str;
                return;
            }
            throw new IllegalArgumentException();
        }

        /* access modifiers changed from: package-private */
        public String getPrefix() {
            return this.prefix;
        }

        /* access modifiers changed from: package-private */
        public String getUri() {
            return this.uri;
        }
    }

    static class QName implements Serializable {
        private static final long serialVersionUID = -6587069811691451077L;
        private String localName;
        private Namespace namespace;

        static QName create(Namespace namespace2, String str) {
            if (str == null || !str.equals("*")) {
                QName qName = new QName();
                qName.namespace = namespace2;
                qName.localName = str;
                return qName;
            }
            throw new RuntimeException("* is not valid localName");
        }

        static QName create(String str, String str2, String str3) {
            return create(Namespace.create(str3, str), str2);
        }

        static String qualify(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("prefix must not be null");
            } else if (str.length() <= 0) {
                return str2;
            } else {
                return str + ":" + str2;
            }
        }

        private QName() {
        }

        public String toString() {
            return "XmlNode.QName [" + this.localName + "," + this.namespace + "]";
        }

        private boolean equals(String str, String str2) {
            if (str == null && str2 == null) {
                return true;
            }
            if (str == null || str2 == null) {
                return false;
            }
            return str.equals(str2);
        }

        private boolean namespacesEqual(Namespace namespace2, Namespace namespace3) {
            if (namespace2 == null && namespace3 == null) {
                return true;
            }
            if (namespace2 == null || namespace3 == null) {
                return false;
            }
            return equals(namespace2.getUri(), namespace3.getUri());
        }

        /* access modifiers changed from: package-private */
        public final boolean equals(QName qName) {
            if (namespacesEqual(this.namespace, qName.namespace) && equals(this.localName, qName.localName)) {
                return true;
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof QName)) {
                return false;
            }
            return equals((QName) obj);
        }

        public int hashCode() {
            String str = this.localName;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        /* access modifiers changed from: package-private */
        public void lookupPrefix(Node node) {
            if (node != null) {
                String lookupPrefix = node.lookupPrefix(this.namespace.getUri());
                if (lookupPrefix == null) {
                    String lookupNamespaceURI = node.lookupNamespaceURI((String) null);
                    if (lookupNamespaceURI == null) {
                        lookupNamespaceURI = "";
                    }
                    if (this.namespace.getUri().equals(lookupNamespaceURI)) {
                        lookupPrefix = "";
                    }
                }
                int i = 0;
                while (lookupPrefix == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("e4x_");
                    int i2 = i + 1;
                    sb.append(i);
                    String sb2 = sb.toString();
                    if (node.lookupNamespaceURI(sb2) == null) {
                        Node node2 = node;
                        while (node2.getParentNode() != null && (node2.getParentNode() instanceof Element)) {
                            node2 = node2.getParentNode();
                        }
                        ((Element) node2).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + sb2, this.namespace.getUri());
                        lookupPrefix = sb2;
                    }
                    i = i2;
                }
                this.namespace.setPrefix(lookupPrefix);
                return;
            }
            throw new IllegalArgumentException("node must not be null");
        }

        /* access modifiers changed from: package-private */
        public String qualify(Node node) {
            if (this.namespace.getPrefix() == null) {
                if (node != null) {
                    lookupPrefix(node);
                } else if (this.namespace.getUri().equals("")) {
                    this.namespace.setPrefix("");
                } else {
                    this.namespace.setPrefix("");
                }
            }
            return qualify(this.namespace.getPrefix(), this.localName);
        }

        /* access modifiers changed from: package-private */
        public void setAttribute(Element element, String str) {
            if (this.namespace.getPrefix() == null) {
                lookupPrefix(element);
            }
            element.setAttributeNS(this.namespace.getUri(), qualify(this.namespace.getPrefix(), this.localName), str);
        }

        /* access modifiers changed from: package-private */
        public Namespace getNamespace() {
            return this.namespace;
        }

        /* access modifiers changed from: package-private */
        public String getLocalName() {
            return this.localName;
        }
    }

    static class InternalList implements Serializable {
        private static final long serialVersionUID = -3633151157292048978L;
        private List<XmlNode> list = new ArrayList();

        InternalList() {
        }

        private void _add(XmlNode xmlNode) {
            this.list.add(xmlNode);
        }

        /* access modifiers changed from: package-private */
        public XmlNode item(int i) {
            return this.list.get(i);
        }

        /* access modifiers changed from: package-private */
        public void remove(int i) {
            this.list.remove(i);
        }

        /* access modifiers changed from: package-private */
        public void add(InternalList internalList) {
            for (int i = 0; i < internalList.length(); i++) {
                _add(internalList.item(i));
            }
        }

        /* access modifiers changed from: package-private */
        public void add(InternalList internalList, int i, int i2) {
            while (i < i2) {
                _add(internalList.item(i));
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public void add(XmlNode xmlNode) {
            _add(xmlNode);
        }

        /* access modifiers changed from: package-private */
        public void add(XML xml) {
            _add(xml.getAnnotation());
        }

        /* access modifiers changed from: package-private */
        public void addToList(Object obj) {
            if (!(obj instanceof Undefined)) {
                if (obj instanceof XMLList) {
                    XMLList xMLList = (XMLList) obj;
                    for (int i = 0; i < xMLList.length(); i++) {
                        _add(xMLList.item(i).getAnnotation());
                    }
                } else if (obj instanceof XML) {
                    _add(((XML) obj).getAnnotation());
                } else if (obj instanceof XmlNode) {
                    _add((XmlNode) obj);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int length() {
            return this.list.size();
        }
    }

    static abstract class Filter {
        static final Filter COMMENT = new Filter() {
            /* access modifiers changed from: package-private */
            public boolean accept(Node node) {
                return node.getNodeType() == 8;
            }
        };
        static Filter ELEMENT = new Filter() {
            /* access modifiers changed from: package-private */
            public boolean accept(Node node) {
                return node.getNodeType() == 1;
            }
        };
        static final Filter TEXT = new Filter() {
            /* access modifiers changed from: package-private */
            public boolean accept(Node node) {
                return node.getNodeType() == 3;
            }
        };
        static Filter TRUE = new Filter() {
            /* access modifiers changed from: package-private */
            public boolean accept(Node node) {
                return true;
            }
        };

        /* access modifiers changed from: package-private */
        public abstract boolean accept(Node node);

        Filter() {
        }

        static Filter PROCESSING_INSTRUCTION(final XMLName xMLName) {
            return new Filter() {
                /* access modifiers changed from: package-private */
                public boolean accept(Node node) {
                    if (node.getNodeType() == 7) {
                        return xMLName.matchesLocalName(((ProcessingInstruction) node).getTarget());
                    }
                    return false;
                }
            };
        }
    }

    /* access modifiers changed from: package-private */
    public Node toDomNode() {
        return this.dom;
    }
}
