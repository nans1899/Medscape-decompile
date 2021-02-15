package org.dom4j.xpath;

import java.io.Serializable;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.jaxen.NamespaceContext;

public class DefaultNamespaceContext implements NamespaceContext, Serializable {
    private final Element element;

    public DefaultNamespaceContext(Element element2) {
        this.element = element2;
    }

    public static DefaultNamespaceContext create(Object obj) {
        Element element2;
        if (obj instanceof Element) {
            element2 = (Element) obj;
        } else if (obj instanceof Document) {
            element2 = ((Document) obj).getRootElement();
        } else {
            element2 = obj instanceof Node ? ((Node) obj).getParent() : null;
        }
        if (element2 != null) {
            return new DefaultNamespaceContext(element2);
        }
        return null;
    }

    public String translateNamespacePrefixToUri(String str) {
        Namespace namespaceForPrefix;
        if (str == null || str.length() <= 0 || (namespaceForPrefix = this.element.getNamespaceForPrefix(str)) == null) {
            return null;
        }
        return namespaceForPrefix.getURI();
    }
}
