package com.bea.xml.stream;

import javax.xml.XMLConstants;
import javax.xml.stream.events.Namespace;

public class NamespaceBase extends AttributeBase implements Namespace {
    boolean declaresDefaultNamespace;

    public int getEventType() {
        return 13;
    }

    public boolean isAttribute() {
        return false;
    }

    public boolean isNamespace() {
        return true;
    }

    public NamespaceBase(String str, String str2) {
        super(XMLConstants.XMLNS_ATTRIBUTE, str, str2);
        this.declaresDefaultNamespace = false;
        this.declaresDefaultNamespace = false;
    }

    public NamespaceBase(String str) {
        super(XMLConstants.XMLNS_ATTRIBUTE, "", str);
        this.declaresDefaultNamespace = false;
        this.declaresDefaultNamespace = true;
    }

    public String getPrefix() {
        if (this.declaresDefaultNamespace) {
            return "";
        }
        return super.getLocalName();
    }

    public String getNamespaceURI() {
        return super.getValue();
    }

    public boolean isDefaultNamespaceDeclaration() {
        return this.declaresDefaultNamespace;
    }

    public String toString() {
        if (this.declaresDefaultNamespace) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("xmlns='");
            stringBuffer.append(getNamespaceURI());
            stringBuffer.append("'");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("xmlns:");
        stringBuffer2.append(getPrefix());
        stringBuffer2.append("='");
        stringBuffer2.append(getNamespaceURI());
        stringBuffer2.append("'");
        return stringBuffer2.toString();
    }
}
