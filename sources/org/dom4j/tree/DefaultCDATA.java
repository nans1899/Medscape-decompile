package org.dom4j.tree;

import org.dom4j.Element;

public class DefaultCDATA extends FlyweightCDATA {
    private Element parent;

    public boolean isReadOnly() {
        return false;
    }

    public boolean supportsParent() {
        return true;
    }

    public DefaultCDATA(String str) {
        super(str);
    }

    public DefaultCDATA(Element element, String str) {
        super(str);
        this.parent = element;
    }

    public void setText(String str) {
        this.text = str;
    }

    public Element getParent() {
        return this.parent;
    }

    public void setParent(Element element) {
        this.parent = element;
    }
}
