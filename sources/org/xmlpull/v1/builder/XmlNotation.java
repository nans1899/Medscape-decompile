package org.xmlpull.v1.builder;

public interface XmlNotation extends XmlContainer {
    String getDeclarationBaseUri();

    String getName();

    String getPublicIdentifier();

    String getSystemIdentifier();
}
