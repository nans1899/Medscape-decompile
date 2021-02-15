package org.xmlpull.v1.builder.impl;

import org.xmlpull.v1.builder.XmlAttribute;
import org.xmlpull.v1.builder.XmlElement;
import org.xmlpull.v1.builder.XmlNamespace;

public class XmlAttributeImpl implements XmlAttribute {
    private boolean default_;
    private String name_;
    private XmlNamespace namespace_;
    private XmlElement owner_;
    private String prefix_;
    private String type_;
    private String value_;

    XmlAttributeImpl(XmlElement xmlElement, String str, String str2) {
        this.type_ = "CDATA";
        this.owner_ = xmlElement;
        this.name_ = str;
        if (str2 != null) {
            this.value_ = str2;
            return;
        }
        throw new IllegalArgumentException("attribute value can not be null");
    }

    XmlAttributeImpl(XmlElement xmlElement, XmlNamespace xmlNamespace, String str, String str2) {
        this(xmlElement, str, str2);
        this.namespace_ = xmlNamespace;
    }

    XmlAttributeImpl(XmlElement xmlElement, String str, XmlNamespace xmlNamespace, String str2, String str3) {
        this(xmlElement, xmlNamespace, str2, str3);
        this.type_ = str;
    }

    XmlAttributeImpl(XmlElement xmlElement, String str, XmlNamespace xmlNamespace, String str2, String str3, boolean z) {
        this(xmlElement, xmlNamespace, str2, str3);
        if (str != null) {
            this.type_ = str;
            this.default_ = !z;
            return;
        }
        throw new IllegalArgumentException("attribute type can not be null");
    }

    public XmlElement getOwner() {
        return this.owner_;
    }

    public XmlNamespace getNamespace() {
        return this.namespace_;
    }

    public String getNamespaceName() {
        XmlNamespace xmlNamespace = this.namespace_;
        if (xmlNamespace != null) {
            return xmlNamespace.getNamespaceName();
        }
        return null;
    }

    public String getName() {
        return this.name_;
    }

    public String getValue() {
        return this.value_;
    }

    public String getType() {
        return this.type_;
    }

    public boolean isSpecified() {
        return !this.default_;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("name=");
        stringBuffer.append(this.name_);
        stringBuffer.append(" value=");
        stringBuffer.append(this.value_);
        return stringBuffer.toString();
    }
}
