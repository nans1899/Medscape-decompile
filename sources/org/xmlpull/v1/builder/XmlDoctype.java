package org.xmlpull.v1.builder;

import java.util.Iterator;

public interface XmlDoctype extends XmlContainer {
    XmlProcessingInstruction addProcessingInstruction(String str, String str2);

    Iterator children();

    XmlDocument getParent();

    String getPublicIdentifier();

    String getSystemIdentifier();

    void removeAllProcessingInstructions();
}
