package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class DefaultAttribute extends FlyweightAttribute {
    private Element parent;

    public boolean isReadOnly() {
        return false;
    }

    public boolean supportsParent() {
        return true;
    }

    public DefaultAttribute(QName qName) {
        super(qName);
    }

    public DefaultAttribute(QName qName, String str) {
        super(qName, str);
    }

    public DefaultAttribute(Element element, QName qName, String str) {
        super(qName, str);
        this.parent = element;
    }

    public DefaultAttribute(String str, String str2) {
        super(str, str2);
    }

    public DefaultAttribute(String str, String str2, Namespace namespace) {
        super(str, str2, namespace);
    }

    public DefaultAttribute(Element element, String str, String str2, Namespace namespace) {
        super(str, str2, namespace);
        this.parent = element;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public Element getParent() {
        return this.parent;
    }

    public void setParent(Element element) {
        this.parent = element;
    }
}
