package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.Visitor;

public abstract class AbstractAttribute extends AbstractNode implements Attribute {
    public short getNodeType() {
        return 2;
    }

    public void setNamespace(Namespace namespace) {
        throw new UnsupportedOperationException("This Attribute is read only and cannot be changed");
    }

    public String getText() {
        return getValue();
    }

    public void setText(String str) {
        setValue(str);
    }

    public void setValue(String str) {
        throw new UnsupportedOperationException("This Attribute is read only and cannot be changed");
    }

    public Object getData() {
        return getValue();
    }

    public void setData(Object obj) {
        setValue(obj == null ? null : obj.toString());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append(" [Attribute: name ");
        stringBuffer.append(getQualifiedName());
        stringBuffer.append(" value \"");
        stringBuffer.append(getValue());
        stringBuffer.append("\"]");
        return stringBuffer.toString();
    }

    public String asXML() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(getQualifiedName()));
        stringBuffer.append("=\"");
        stringBuffer.append(getValue());
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    public void write(Writer writer) throws IOException {
        writer.write(getQualifiedName());
        writer.write("=\"");
        writer.write(getValue());
        writer.write("\"");
    }

    public void accept(Visitor visitor) {
        visitor.visit((Attribute) this);
    }

    public Namespace getNamespace() {
        return getQName().getNamespace();
    }

    public String getName() {
        return getQName().getName();
    }

    public String getNamespacePrefix() {
        return getQName().getNamespacePrefix();
    }

    public String getNamespaceURI() {
        return getQName().getNamespaceURI();
    }

    public String getQualifiedName() {
        return getQName().getQualifiedName();
    }

    public String getPath(Element element) {
        StringBuffer stringBuffer = new StringBuffer();
        Element parent = getParent();
        if (!(parent == null || parent == element)) {
            stringBuffer.append(parent.getPath(element));
            stringBuffer.append("/");
        }
        stringBuffer.append("@");
        String namespaceURI = getNamespaceURI();
        String namespacePrefix = getNamespacePrefix();
        if (namespaceURI == null || namespaceURI.length() == 0 || namespacePrefix == null || namespacePrefix.length() == 0) {
            stringBuffer.append(getName());
        } else {
            stringBuffer.append(getQualifiedName());
        }
        return stringBuffer.toString();
    }

    public String getUniquePath(Element element) {
        StringBuffer stringBuffer = new StringBuffer();
        Element parent = getParent();
        if (!(parent == null || parent == element)) {
            stringBuffer.append(parent.getUniquePath(element));
            stringBuffer.append("/");
        }
        stringBuffer.append("@");
        String namespaceURI = getNamespaceURI();
        String namespacePrefix = getNamespacePrefix();
        if (namespaceURI == null || namespaceURI.length() == 0 || namespacePrefix == null || namespacePrefix.length() == 0) {
            stringBuffer.append(getName());
        } else {
            stringBuffer.append(getQualifiedName());
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public Node createXPathResult(Element element) {
        return new DefaultAttribute(element, getQName(), getValue());
    }
}
