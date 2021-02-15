package org.dom4j.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DOMAttributeNodeMap implements NamedNodeMap {
    private DOMElement element;

    public DOMAttributeNodeMap(DOMElement dOMElement) {
        this.element = dOMElement;
    }

    public void foo() throws DOMException {
        DOMNodeHelper.notSupported();
    }

    public Node getNamedItem(String str) {
        return this.element.getAttributeNode(str);
    }

    public Node setNamedItem(Node node) throws DOMException {
        if (node instanceof Attr) {
            return this.element.setAttributeNode((Attr) node);
        }
        StringBuffer stringBuffer = new StringBuffer("Node is not an Attr: ");
        stringBuffer.append(node);
        throw new DOMException(9, stringBuffer.toString());
    }

    public Node removeNamedItem(String str) throws DOMException {
        Attr attributeNode = this.element.getAttributeNode(str);
        if (attributeNode != null) {
            return this.element.removeAttributeNode(attributeNode);
        }
        StringBuffer stringBuffer = new StringBuffer("No attribute named ");
        stringBuffer.append(str);
        throw new DOMException(8, stringBuffer.toString());
    }

    public Node item(int i) {
        return DOMNodeHelper.asDOMAttr(this.element.attribute(i));
    }

    public int getLength() {
        return this.element.attributeCount();
    }

    public Node getNamedItemNS(String str, String str2) {
        return this.element.getAttributeNodeNS(str, str2);
    }

    public Node setNamedItemNS(Node node) throws DOMException {
        if (node instanceof Attr) {
            return this.element.setAttributeNodeNS((Attr) node);
        }
        StringBuffer stringBuffer = new StringBuffer("Node is not an Attr: ");
        stringBuffer.append(node);
        throw new DOMException(9, stringBuffer.toString());
    }

    public Node removeNamedItemNS(String str, String str2) throws DOMException {
        Attr attributeNodeNS = this.element.getAttributeNodeNS(str, str2);
        return attributeNodeNS != null ? this.element.removeAttributeNode(attributeNodeNS) : attributeNodeNS;
    }
}
