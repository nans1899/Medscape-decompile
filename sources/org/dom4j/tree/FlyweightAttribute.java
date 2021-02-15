package org.dom4j.tree;

import org.dom4j.Namespace;
import org.dom4j.QName;

public class FlyweightAttribute extends AbstractAttribute {
    private QName qname;
    protected String value;

    public FlyweightAttribute(QName qName) {
        this.qname = qName;
    }

    public FlyweightAttribute(QName qName, String str) {
        this.qname = qName;
        this.value = str;
    }

    public FlyweightAttribute(String str, String str2) {
        this.qname = getDocumentFactory().createQName(str);
        this.value = str2;
    }

    public FlyweightAttribute(String str, String str2, Namespace namespace) {
        this.qname = getDocumentFactory().createQName(str, namespace);
        this.value = str2;
    }

    public String getValue() {
        return this.value;
    }

    public QName getQName() {
        return this.qname;
    }
}
