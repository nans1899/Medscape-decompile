package org.xmlpull.v1.builder;

public interface XmlProcessingInstruction extends XmlContainer {
    String getBaseUri();

    String getContent();

    XmlNotation getNotation();

    XmlContainer getParent();

    String getTarget();
}
