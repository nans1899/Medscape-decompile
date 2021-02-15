package org.jaxen;

import java.io.Serializable;

class QualifiedName implements Serializable {
    private static final long serialVersionUID = 2734958615642751535L;
    private String localName;
    private String namespaceURI;

    QualifiedName(String str, String str2) {
        this.namespaceURI = str == null ? "" : str;
        this.localName = str2;
    }

    public int hashCode() {
        return this.localName.hashCode() ^ this.namespaceURI.hashCode();
    }

    public boolean equals(Object obj) {
        QualifiedName qualifiedName = (QualifiedName) obj;
        return this.namespaceURI.equals(qualifiedName.namespaceURI) && qualifiedName.localName.equals(this.localName);
    }

    /* access modifiers changed from: package-private */
    public String getClarkForm() {
        if ("".equals(this.namespaceURI)) {
            return this.localName;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append(this.namespaceURI);
        stringBuffer.append("}");
        stringBuffer.append(":");
        stringBuffer.append(this.localName);
        return stringBuffer.toString();
    }
}
