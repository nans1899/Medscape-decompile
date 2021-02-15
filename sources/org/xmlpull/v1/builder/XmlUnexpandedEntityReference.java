package org.xmlpull.v1.builder;

public interface XmlUnexpandedEntityReference extends XmlContainer {
    String getDeclarationBaseUri();

    String getName();

    XmlElement getParent();

    String getPublicIdentifier();

    String getSystemIdentifier();
}
