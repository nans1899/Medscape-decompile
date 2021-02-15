package org.xmlpull.v1.builder.impl;

import org.xmlpull.v1.builder.XmlBuilderException;
import org.xmlpull.v1.builder.XmlNamespace;

public class XmlNamespaceImpl implements XmlNamespace {
    private String namespaceName;
    private String prefix;

    XmlNamespaceImpl(String str, String str2) {
        this.prefix = str;
        if (str2 != null) {
            this.namespaceName = str2;
            return;
        }
        throw new XmlBuilderException("namespace name can not be null");
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getNamespaceName() {
        return this.namespaceName;
    }
}
