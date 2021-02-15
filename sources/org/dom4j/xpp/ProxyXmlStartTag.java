package org.dom4j.xpp;

import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.XMLConstants;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.AbstractElement;
import org.gjt.xpp.XmlPullParserException;
import org.gjt.xpp.XmlStartTag;

public class ProxyXmlStartTag implements XmlStartTag {
    private Element element;
    private DocumentFactory factory = DocumentFactory.getInstance();

    public ProxyXmlStartTag() {
    }

    public ProxyXmlStartTag(Element element2) {
        this.element = element2;
    }

    public void resetStartTag() {
        this.element = null;
    }

    public int getAttributeCount() {
        Element element2 = this.element;
        if (element2 != null) {
            return element2.attributeCount();
        }
        return 0;
    }

    public String getAttributeNamespaceUri(int i) {
        Attribute attribute;
        Element element2 = this.element;
        if (element2 == null || (attribute = element2.attribute(i)) == null) {
            return null;
        }
        return attribute.getNamespaceURI();
    }

    public String getAttributeLocalName(int i) {
        Attribute attribute;
        Element element2 = this.element;
        if (element2 == null || (attribute = element2.attribute(i)) == null) {
            return null;
        }
        return attribute.getName();
    }

    public String getAttributePrefix(int i) {
        Attribute attribute;
        String namespacePrefix;
        Element element2 = this.element;
        if (element2 == null || (attribute = element2.attribute(i)) == null || (namespacePrefix = attribute.getNamespacePrefix()) == null || namespacePrefix.length() <= 0) {
            return null;
        }
        return namespacePrefix;
    }

    public String getAttributeRawName(int i) {
        Attribute attribute;
        Element element2 = this.element;
        if (element2 == null || (attribute = element2.attribute(i)) == null) {
            return null;
        }
        return attribute.getQualifiedName();
    }

    public String getAttributeValue(int i) {
        Attribute attribute;
        Element element2 = this.element;
        if (element2 == null || (attribute = element2.attribute(i)) == null) {
            return null;
        }
        return attribute.getValue();
    }

    public String getAttributeValueFromRawName(String str) {
        Element element2 = this.element;
        if (element2 == null) {
            return null;
        }
        Iterator attributeIterator = element2.attributeIterator();
        while (attributeIterator.hasNext()) {
            Attribute attribute = (Attribute) attributeIterator.next();
            if (str.equals(attribute.getQualifiedName())) {
                return attribute.getValue();
            }
        }
        return null;
    }

    public String getAttributeValueFromName(String str, String str2) {
        Element element2 = this.element;
        if (element2 == null) {
            return null;
        }
        Iterator attributeIterator = element2.attributeIterator();
        while (attributeIterator.hasNext()) {
            Attribute attribute = (Attribute) attributeIterator.next();
            if (str.equals(attribute.getNamespaceURI()) && str2.equals(attribute.getName())) {
                return attribute.getValue();
            }
        }
        return null;
    }

    public boolean isAttributeNamespaceDeclaration(int i) {
        Attribute attribute;
        Element element2 = this.element;
        if (element2 == null || (attribute = element2.attribute(i)) == null) {
            return false;
        }
        return XMLConstants.XMLNS_ATTRIBUTE.equals(attribute.getNamespacePrefix());
    }

    public void addAttribute(String str, String str2, String str3, String str4) throws XmlPullParserException {
        this.element.addAttribute(QName.get(str3, str), str4);
    }

    public void addAttribute(String str, String str2, String str3, String str4, boolean z) throws XmlPullParserException {
        if (z) {
            int indexOf = str3.indexOf(58);
            this.element.addNamespace(indexOf > 0 ? str3.substring(0, indexOf) : "", str);
            return;
        }
        this.element.addAttribute(QName.get(str3, str), str4);
    }

    public void ensureAttributesCapacity(int i) throws XmlPullParserException {
        Element element2 = this.element;
        if (element2 instanceof AbstractElement) {
            ((AbstractElement) element2).ensureAttributesCapacity(i);
        }
    }

    public void removeAtttributes() throws XmlPullParserException {
        removeAttributes();
    }

    public void removeAttributes() throws XmlPullParserException {
        Element element2 = this.element;
        if (element2 != null) {
            element2.setAttributes(new ArrayList());
        }
    }

    public String getLocalName() {
        return this.element.getName();
    }

    public String getNamespaceUri() {
        return this.element.getNamespaceURI();
    }

    public String getPrefix() {
        return this.element.getNamespacePrefix();
    }

    public String getRawName() {
        return this.element.getQualifiedName();
    }

    public void modifyTag(String str, String str2, String str3) {
        this.element = this.factory.createElement(str3, str);
    }

    public void resetTag() {
        this.element = null;
    }

    public boolean removeAttributeByName(String str, String str2) throws XmlPullParserException {
        if (this.element == null) {
            return false;
        }
        return this.element.remove(this.element.attribute(QName.get(str2, str)));
    }

    public boolean removeAttributeByRawName(String str) throws XmlPullParserException {
        Element element2 = this.element;
        if (element2 == null) {
            return false;
        }
        Attribute attribute = null;
        Iterator attributeIterator = element2.attributeIterator();
        while (true) {
            if (attributeIterator.hasNext()) {
                Attribute attribute2 = (Attribute) attributeIterator.next();
                if (attribute2.getQualifiedName().equals(str)) {
                    attribute = attribute2;
                    break;
                }
            } else {
                break;
            }
        }
        return this.element.remove(attribute);
    }

    public DocumentFactory getDocumentFactory() {
        return this.factory;
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.factory = documentFactory;
    }

    public Element getElement() {
        return this.element;
    }
}
