package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Namespace;

public class DefaultNamespace extends Namespace {
    private Element parent;

    public boolean isReadOnly() {
        return false;
    }

    public boolean supportsParent() {
        return true;
    }

    public DefaultNamespace(String str, String str2) {
        super(str, str2);
    }

    public DefaultNamespace(Element element, String str, String str2) {
        super(str, str2);
        this.parent = element;
    }

    /* access modifiers changed from: protected */
    public int createHashCode() {
        int createHashCode = super.createHashCode();
        Element element = this.parent;
        return element != null ? createHashCode ^ element.hashCode() : createHashCode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultNamespace) || ((DefaultNamespace) obj).parent != this.parent) {
            return false;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Element getParent() {
        return this.parent;
    }

    public void setParent(Element element) {
        this.parent = element;
    }
}
