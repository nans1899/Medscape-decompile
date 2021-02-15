package org.jaxen.jdom;

import org.jdom.Element;
import org.jdom.Namespace;

public class XPathNamespace {
    private Element jdomElement;
    private Namespace jdomNamespace;

    public XPathNamespace(Namespace namespace) {
        this.jdomNamespace = namespace;
    }

    public XPathNamespace(Element element, Namespace namespace) {
        this.jdomElement = element;
        this.jdomNamespace = namespace;
    }

    public Element getJDOMElement() {
        return this.jdomElement;
    }

    public void setJDOMElement(Element element) {
        this.jdomElement = element;
    }

    public Namespace getJDOMNamespace() {
        return this.jdomNamespace;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[xmlns:");
        stringBuffer.append(this.jdomNamespace.getPrefix());
        stringBuffer.append("=\"");
        stringBuffer.append(this.jdomNamespace.getURI());
        stringBuffer.append("\", element=");
        stringBuffer.append(this.jdomElement.getName());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
