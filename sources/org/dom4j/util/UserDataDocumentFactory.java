package org.dom4j.util;

import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

public class UserDataDocumentFactory extends DocumentFactory {
    protected static transient UserDataDocumentFactory singleton = new UserDataDocumentFactory();

    public static DocumentFactory getInstance() {
        return singleton;
    }

    public Element createElement(QName qName) {
        return new UserDataElement(qName);
    }

    public Attribute createAttribute(Element element, QName qName, String str) {
        return new UserDataAttribute(qName, str);
    }
}
