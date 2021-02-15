package org.xmlpull.v1.builder;

public interface XmlCharacters extends XmlContainer {
    XmlElement getParent();

    String getText();

    Boolean isWhitespaceContent();
}
