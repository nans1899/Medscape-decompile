package org.xmlpull.v1.builder;

import java.util.Iterator;

public interface XmlDocument extends XmlContainer {
    void addChild(Object obj);

    XmlComment addComment(String str);

    XmlDoctype addDoctype(String str, String str2);

    XmlElement addDocumentElement(String str);

    XmlElement addDocumentElement(XmlNamespace xmlNamespace, String str);

    XmlNotation addNotation(String str, String str2, String str3, String str4);

    XmlProcessingInstruction addProcessingInstruction(String str, String str2);

    Iterator children();

    String getBaseUri();

    String getCharacterEncodingScheme();

    XmlElement getDocumentElement();

    String getVersion();

    void insertChild(int i, Object obj);

    boolean isAllDeclarationsProcessed();

    Boolean isStandalone();

    XmlComment newComment(String str);

    XmlDoctype newDoctype(String str, String str2);

    XmlProcessingInstruction newProcessingInstruction(String str, String str2);

    Iterator notations();

    void remocveAllUnparsedEntities();

    void removeAllChildren();

    void removeAllNotations();

    void setCharacterEncodingScheme(String str);

    void setDocumentElement(XmlElement xmlElement);

    Iterator unparsedEntities();
}
