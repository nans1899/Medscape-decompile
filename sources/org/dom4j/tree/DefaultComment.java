package org.dom4j.tree;

import org.dom4j.Element;

public class DefaultComment extends FlyweightComment {
    private Element parent;

    public boolean isReadOnly() {
        return false;
    }

    public boolean supportsParent() {
        return true;
    }

    public DefaultComment(String str) {
        super(str);
    }

    public DefaultComment(Element element, String str) {
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
