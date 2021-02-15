package org.dom4j.util;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

public class IndexedDocumentFactory extends DocumentFactory {
    protected static transient IndexedDocumentFactory singleton = new IndexedDocumentFactory();

    public static DocumentFactory getInstance() {
        return singleton;
    }

    public Element createElement(QName qName) {
        return new IndexedElement(qName);
    }

    public Element createElement(QName qName, int i) {
        return new IndexedElement(qName, i);
    }
}
