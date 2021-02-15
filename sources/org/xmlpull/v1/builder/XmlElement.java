package org.xmlpull.v1.builder;

import java.util.Iterator;

public interface XmlElement extends XmlContainer {
    public static final String NO_NAMESPACE = "";

    XmlAttribute addAttribute(String str, String str2);

    XmlAttribute addAttribute(String str, String str2, String str3, String str4, String str5, boolean z);

    XmlAttribute addAttribute(String str, XmlNamespace xmlNamespace, String str2, String str3);

    XmlAttribute addAttribute(String str, XmlNamespace xmlNamespace, String str2, String str3, boolean z);

    XmlAttribute addAttribute(XmlAttribute xmlAttribute);

    XmlAttribute addAttribute(XmlNamespace xmlNamespace, String str, String str2);

    void addChild(int i, Object obj);

    void addChild(Object obj);

    XmlElement addElement(String str);

    XmlElement addElement(XmlElement xmlElement);

    XmlElement addElement(XmlNamespace xmlNamespace, String str);

    Iterator attributes();

    Iterator children();

    XmlNamespace declareNamespace(String str, String str2);

    XmlNamespace declareNamespace(XmlNamespace xmlNamespace);

    void ensureAttributeCapacity(int i);

    void ensureChildrenCapacity(int i);

    void ensureNamespaceDeclarationsCapacity(int i);

    XmlAttribute findAttribute(String str, String str2);

    XmlElement findElementByName(String str);

    XmlElement findElementByName(String str, String str2);

    XmlElement findElementByName(String str, String str2, XmlElement xmlElement);

    XmlElement findElementByName(String str, XmlElement xmlElement);

    String getBaseUri();

    String getName();

    XmlNamespace getNamespace();

    String getNamespaceName();

    XmlContainer getParent();

    boolean hasAttributes();

    boolean hasChild(Object obj);

    boolean hasChildren();

    boolean hasNamespaceDeclarations();

    void insertChild(int i, Object obj);

    XmlNamespace lookupNamespaceByName(String str);

    XmlNamespace lookupNamespaceByPrefix(String str);

    Iterator namespaces();

    XmlElement newElement(String str);

    XmlElement newElement(String str, String str2);

    XmlElement newElement(XmlNamespace xmlNamespace, String str);

    XmlNamespace newNamespace(String str);

    XmlNamespace newNamespace(String str, String str2);

    void removeAllAttributes();

    void removeAllChildren();

    void removeAllNamespaceDeclarations();

    void removeAttribute(XmlAttribute xmlAttribute);

    void removeChild(Object obj);

    void replaceChild(Object obj, Object obj2);

    void setBaseUri(String str);

    void setName(String str);

    void setNamespace(XmlNamespace xmlNamespace);

    void setParent(XmlContainer xmlContainer);
}
