package org.dom4j.util;

import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.BaseElement;

public class NonLazyElement extends BaseElement {
    public NonLazyElement(String str) {
        super(str);
        this.attributes = createAttributeList();
        this.content = createContentList();
    }

    public NonLazyElement(QName qName) {
        super(qName);
        this.attributes = createAttributeList();
        this.content = createContentList();
    }

    public NonLazyElement(String str, Namespace namespace) {
        super(str, namespace);
        this.attributes = createAttributeList();
        this.content = createContentList();
    }

    public NonLazyElement(QName qName, int i) {
        super(qName);
        this.attributes = createAttributeList(i);
        this.content = createContentList();
    }
}
