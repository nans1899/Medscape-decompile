package org.dom4j.dom;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMElement extends DefaultElement implements Element {
    private static final DocumentFactory DOCUMENT_FACTORY = DOMDocumentFactory.getInstance();

    public String getNodeValue() throws DOMException {
        return null;
    }

    public void setNodeValue(String str) throws DOMException {
    }

    public DOMElement(String str) {
        super(str);
    }

    public DOMElement(QName qName) {
        super(qName);
    }

    public DOMElement(QName qName, int i) {
        super(qName, i);
    }

    public DOMElement(String str, Namespace namespace) {
        super(str, namespace);
    }

    public boolean supports(String str, String str2) {
        return DOMNodeHelper.supports(this, str, str2);
    }

    public String getNamespaceURI() {
        return getQName().getNamespaceURI();
    }

    public String getPrefix() {
        return getQName().getNamespacePrefix();
    }

    public void setPrefix(String str) throws DOMException {
        DOMNodeHelper.setPrefix(this, str);
    }

    public String getLocalName() {
        return getQName().getName();
    }

    public String getNodeName() {
        return getName();
    }

    public Node getParentNode() {
        return DOMNodeHelper.getParentNode(this);
    }

    public NodeList getChildNodes() {
        return DOMNodeHelper.createNodeList(content());
    }

    public Node getFirstChild() {
        return DOMNodeHelper.asDOMNode(node(0));
    }

    public Node getLastChild() {
        return DOMNodeHelper.asDOMNode(node(nodeCount() - 1));
    }

    public Node getPreviousSibling() {
        return DOMNodeHelper.getPreviousSibling(this);
    }

    public Node getNextSibling() {
        return DOMNodeHelper.getNextSibling(this);
    }

    public NamedNodeMap getAttributes() {
        return new DOMAttributeNodeMap(this);
    }

    public Document getOwnerDocument() {
        return DOMNodeHelper.getOwnerDocument(this);
    }

    public Node insertBefore(Node node, Node node2) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.insertBefore(this, node, node2);
    }

    public Node replaceChild(Node node, Node node2) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.replaceChild(this, node, node2);
    }

    public Node removeChild(Node node) throws DOMException {
        return DOMNodeHelper.removeChild(this, node);
    }

    public Node appendChild(Node node) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.appendChild(this, node);
    }

    private void checkNewChildNode(Node node) throws DOMException {
        short nodeType = node.getNodeType();
        if (nodeType != 1 && nodeType != 3 && nodeType != 8 && nodeType != 7 && nodeType != 4 && nodeType != 5) {
            throw new DOMException(3, "Given node cannot be a child of element");
        }
    }

    public boolean hasChildNodes() {
        return nodeCount() > 0;
    }

    public Node cloneNode(boolean z) {
        return DOMNodeHelper.cloneNode(this, z);
    }

    public boolean isSupported(String str, String str2) {
        return DOMNodeHelper.isSupported(this, str, str2);
    }

    public boolean hasAttributes() {
        return DOMNodeHelper.hasAttributes(this);
    }

    public String getTagName() {
        return getName();
    }

    public String getAttribute(String str) {
        String attributeValue = attributeValue(str);
        return attributeValue != null ? attributeValue : "";
    }

    public void setAttribute(String str, String str2) throws DOMException {
        addAttribute(str, str2);
    }

    public void removeAttribute(String str) throws DOMException {
        Attribute attribute = attribute(str);
        if (attribute != null) {
            remove(attribute);
        }
    }

    public Attr getAttributeNode(String str) {
        return DOMNodeHelper.asDOMAttr(attribute(str));
    }

    public Attr setAttributeNode(Attr attr) throws DOMException {
        if (!isReadOnly()) {
            Attribute attribute = attribute(attr);
            if (attribute != attr) {
                if (attr.getOwnerElement() == null) {
                    Attribute createAttribute = createAttribute(attr);
                    if (attribute != null) {
                        attribute.detach();
                    }
                    add(createAttribute);
                } else {
                    throw new DOMException(10, "Attribute is already in use");
                }
            }
            return DOMNodeHelper.asDOMAttr(attribute);
        }
        throw new DOMException(7, "No modification allowed");
    }

    public Attr removeAttributeNode(Attr attr) throws DOMException {
        Attribute attribute = attribute(attr);
        if (attribute != null) {
            attribute.detach();
            return DOMNodeHelper.asDOMAttr(attribute);
        }
        throw new DOMException(8, "No such attribute");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r1.getValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAttributeNS(java.lang.String r1, java.lang.String r2) {
        /*
            r0 = this;
            org.dom4j.Attribute r1 = r0.attribute(r1, r2)
            if (r1 == 0) goto L_0x000d
            java.lang.String r1 = r1.getValue()
            if (r1 == 0) goto L_0x000d
            return r1
        L_0x000d:
            java.lang.String r1 = ""
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.dom.DOMElement.getAttributeNS(java.lang.String, java.lang.String):java.lang.String");
    }

    public void setAttributeNS(String str, String str2, String str3) throws DOMException {
        Attribute attribute = attribute(str, str2);
        if (attribute != null) {
            attribute.setValue(str3);
        } else {
            addAttribute(getQName(str, str2), str3);
        }
    }

    public void removeAttributeNS(String str, String str2) throws DOMException {
        Attribute attribute = attribute(str, str2);
        if (attribute != null) {
            remove(attribute);
        }
    }

    public Attr getAttributeNodeNS(String str, String str2) {
        Attribute attribute = attribute(str, str2);
        if (attribute == null) {
            return null;
        }
        DOMNodeHelper.asDOMAttr(attribute);
        return null;
    }

    public Attr setAttributeNodeNS(Attr attr) throws DOMException {
        Attribute attribute = attribute(attr.getNamespaceURI(), attr.getLocalName());
        if (attribute != null) {
            attribute.setValue(attr.getValue());
        } else {
            attribute = createAttribute(attr);
            add(attribute);
        }
        return DOMNodeHelper.asDOMAttr(attribute);
    }

    public NodeList getElementsByTagName(String str) {
        ArrayList arrayList = new ArrayList();
        DOMNodeHelper.appendElementsByTagName(arrayList, this, str);
        return DOMNodeHelper.createNodeList(arrayList);
    }

    public NodeList getElementsByTagNameNS(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        DOMNodeHelper.appendElementsByTagNameNS(arrayList, this, str, str2);
        return DOMNodeHelper.createNodeList(arrayList);
    }

    public boolean hasAttribute(String str) {
        return attribute(str) != null;
    }

    public boolean hasAttributeNS(String str, String str2) {
        return attribute(str, str2) != null;
    }

    /* access modifiers changed from: protected */
    public DocumentFactory getDocumentFactory() {
        DocumentFactory documentFactory = getQName().getDocumentFactory();
        return documentFactory != null ? documentFactory : DOCUMENT_FACTORY;
    }

    /* access modifiers changed from: protected */
    public Attribute attribute(Attr attr) {
        return attribute(DOCUMENT_FACTORY.createQName(attr.getLocalName(), attr.getPrefix(), attr.getNamespaceURI()));
    }

    /* access modifiers changed from: protected */
    public Attribute attribute(String str, String str2) {
        List attributeList = attributeList();
        int size = attributeList.size();
        for (int i = 0; i < size; i++) {
            Attribute attribute = (Attribute) attributeList.get(i);
            if (str2.equals(attribute.getName()) && (((str == null || str.length() == 0) && (attribute.getNamespaceURI() == null || attribute.getNamespaceURI().length() == 0)) || (str != null && str.equals(attribute.getNamespaceURI())))) {
                return attribute;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Attribute createAttribute(Attr attr) {
        QName qName;
        String localName = attr.getLocalName();
        if (localName != null) {
            qName = getDocumentFactory().createQName(localName, attr.getPrefix(), attr.getNamespaceURI());
        } else {
            qName = getDocumentFactory().createQName(attr.getName());
        }
        return new DOMAttribute(qName, attr.getValue());
    }

    /* access modifiers changed from: protected */
    public QName getQName(String str, String str2) {
        String str3;
        int indexOf = str2.indexOf(58);
        if (indexOf >= 0) {
            str3 = str2.substring(0, indexOf);
            str2 = str2.substring(indexOf + 1);
        } else {
            str3 = "";
        }
        return getDocumentFactory().createQName(str2, str3, str);
    }
}
