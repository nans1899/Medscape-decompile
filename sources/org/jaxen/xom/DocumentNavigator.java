package org.jaxen.xom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.XMLConstants;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Comment;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.NodeFactory;
import nu.xom.ParentNode;
import nu.xom.ProcessingInstruction;
import nu.xom.Text;
import org.jaxen.BaseXPath;
import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenConstants;
import org.jaxen.UnsupportedAxisException;
import org.jaxen.XPath;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.util.SingleObjectIterator;

public class DocumentNavigator extends DefaultNavigator {
    private static final long serialVersionUID = 3159311338575942877L;

    public boolean isAttribute(Object obj) {
        return obj instanceof Attribute;
    }

    public boolean isComment(Object obj) {
        return obj instanceof Comment;
    }

    public boolean isDocument(Object obj) {
        return obj instanceof Document;
    }

    public boolean isElement(Object obj) {
        return obj instanceof Element;
    }

    public boolean isNamespace(Object obj) {
        return obj instanceof XPathNamespace;
    }

    public boolean isProcessingInstruction(Object obj) {
        return obj instanceof ProcessingInstruction;
    }

    public boolean isText(Object obj) {
        return obj instanceof Text;
    }

    public String getAttributeName(Object obj) {
        if (isAttribute(obj)) {
            return ((Attribute) obj).getLocalName();
        }
        return null;
    }

    public String getAttributeNamespaceUri(Object obj) {
        if (isAttribute(obj)) {
            return ((Attribute) obj).getNamespaceURI();
        }
        return null;
    }

    public String getAttributeQName(Object obj) {
        if (isAttribute(obj)) {
            return ((Attribute) obj).getQualifiedName();
        }
        return null;
    }

    public String getAttributeStringValue(Object obj) {
        if (isAttribute(obj)) {
            return ((Attribute) obj).getValue();
        }
        return null;
    }

    public String getCommentStringValue(Object obj) {
        if (isComment(obj)) {
            return ((Comment) obj).getValue();
        }
        return null;
    }

    public String getElementName(Object obj) {
        if (isElement(obj)) {
            return ((Element) obj).getLocalName();
        }
        return null;
    }

    public String getElementNamespaceUri(Object obj) {
        if (isElement(obj)) {
            return ((Element) obj).getNamespaceURI();
        }
        return null;
    }

    public String getElementQName(Object obj) {
        if (isElement(obj)) {
            return ((Element) obj).getQualifiedName();
        }
        return null;
    }

    public String getElementStringValue(Object obj) {
        if (obj instanceof Node) {
            return ((Node) obj).getValue();
        }
        return null;
    }

    public String getNamespacePrefix(Object obj) {
        if (isElement(obj)) {
            return ((Element) obj).getNamespacePrefix();
        }
        if (isAttribute(obj)) {
            return ((Attribute) obj).getNamespacePrefix();
        }
        if (obj instanceof XPathNamespace) {
            return ((XPathNamespace) obj).getNamespacePrefix();
        }
        return null;
    }

    public String getNamespaceStringValue(Object obj) {
        if (isElement(obj)) {
            return ((Element) obj).getNamespaceURI();
        }
        if (isAttribute(obj)) {
            return ((Attribute) obj).getNamespaceURI();
        }
        if (obj instanceof XPathNamespace) {
            return ((XPathNamespace) obj).getNamespaceURI();
        }
        return null;
    }

    public String getTextStringValue(Object obj) {
        if (obj instanceof Text) {
            return ((Text) obj).getValue();
        }
        return null;
    }

    public Object getDocument(String str) throws FunctionCallException {
        try {
            return new Builder(new NodeFactory()).build(str);
        } catch (Exception e) {
            throw new FunctionCallException((Throwable) e);
        }
    }

    public Object getDocumentNode(Object obj) {
        ParentNode parentNode;
        if (obj instanceof ParentNode) {
            parentNode = (ParentNode) obj;
        } else {
            parentNode = obj instanceof Node ? ((Node) obj).getParent() : null;
        }
        return parentNode.getDocument();
    }

    private static abstract class IndexIterator implements Iterator {
        private int end = -1;
        private Object o = null;
        private int pos = 0;

        public abstract Object get(Object obj, int i);

        public IndexIterator(Object obj, int i, int i2) {
            this.o = obj;
            this.pos = i;
            this.end = i2;
        }

        public boolean hasNext() {
            return this.pos < this.end;
        }

        public Object next() {
            Object obj = this.o;
            int i = this.pos;
            this.pos = i + 1;
            return get(obj, i);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator getAttributeAxisIterator(Object obj) {
        if (isElement(obj)) {
            return new IndexIterator(this, obj, 0, ((Element) obj).getAttributeCount()) {
                private final /* synthetic */ DocumentNavigator this$0;

                {
                    this.this$0 = r1;
                }

                public Object get(Object obj, int i) {
                    return ((Element) obj).getAttribute(i);
                }
            };
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getChildAxisIterator(Object obj) {
        if (isElement(obj) || (obj instanceof Document)) {
            return new IndexIterator(this, obj, 0, ((ParentNode) obj).getChildCount()) {
                private final /* synthetic */ DocumentNavigator this$0;

                {
                    this.this$0 = r1;
                }

                public Object get(Object obj, int i) {
                    return ((ParentNode) obj).getChild(i);
                }
            };
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getParentAxisIterator(Object obj) {
        ParentNode parentNode;
        if (obj instanceof Node) {
            parentNode = ((Node) obj).getParent();
        } else {
            parentNode = isNamespace(obj) ? ((XPathNamespace) obj).getElement() : null;
        }
        if (parentNode != null) {
            return new SingleObjectIterator(parentNode);
        }
        return null;
    }

    public Object getParentNode(Object obj) {
        if (obj instanceof Node) {
            return ((Node) obj).getParent();
        }
        return null;
    }

    public Iterator getPrecedingAxisIterator(Object obj) throws UnsupportedAxisException {
        return super.getPrecedingAxisIterator(obj);
    }

    public Iterator getPrecedingSiblingAxisIterator(Object obj) throws UnsupportedAxisException {
        return super.getPrecedingSiblingAxisIterator(obj);
    }

    public String getProcessingInstructionData(Object obj) {
        if (obj instanceof ProcessingInstruction) {
            return ((ProcessingInstruction) obj).getValue();
        }
        return null;
    }

    public String getProcessingInstructionTarget(Object obj) {
        if (obj instanceof ProcessingInstruction) {
            return ((ProcessingInstruction) obj).getTarget();
        }
        return null;
    }

    public String translateNamespacePrefixToUri(String str, Object obj) {
        Element element;
        if (obj instanceof Element) {
            element = (Element) obj;
        } else {
            if (!(obj instanceof ParentNode)) {
                if (obj instanceof Node) {
                    element = (Element) ((Node) obj).getParent();
                } else if (obj instanceof XPathNamespace) {
                    element = ((XPathNamespace) obj).getElement();
                }
            }
            element = null;
        }
        if (element != null) {
            return element.getNamespaceURI(str);
        }
        return null;
    }

    public XPath parseXPath(String str) throws SAXPathException {
        return new BaseXPath(str, this);
    }

    private static class XPathNamespace {
        private Element element;
        private String prefix;
        private String uri;

        public XPathNamespace(Element element2, String str, String str2) {
            this.element = element2;
            this.uri = str;
            this.prefix = str2;
        }

        public Element getElement() {
            return this.element;
        }

        public String getNamespaceURI() {
            return this.uri;
        }

        public String getNamespacePrefix() {
            return this.prefix;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[xmlns:");
            stringBuffer.append(this.prefix);
            stringBuffer.append("=\"");
            stringBuffer.append(this.uri);
            stringBuffer.append("\", element=");
            stringBuffer.append(this.element.getLocalName());
            stringBuffer.append("]");
            return stringBuffer.toString();
        }
    }

    private boolean addNamespaceForElement(Element element, String str, String str2, Map map) {
        if (str == null || str.length() <= 0 || map.containsKey(str2)) {
            return false;
        }
        map.put(str2, new XPathNamespace(element, str, str2));
        return true;
    }

    public Iterator getNamespaceAxisIterator(Object obj) {
        if (!isElement(obj)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        HashMap hashMap = new HashMap();
        Element element = (Element) obj;
        Element element2 = element;
        while (element instanceof Element) {
            element2 = element;
            addNamespaceForElement(element2, element2.getNamespaceURI(), element2.getNamespacePrefix(), hashMap);
            int namespaceDeclarationCount = element2.getNamespaceDeclarationCount();
            for (int i = 0; i < namespaceDeclarationCount; i++) {
                String namespacePrefix = element2.getNamespacePrefix(i);
                addNamespaceForElement(element2, element2.getNamespaceURI(namespacePrefix), namespacePrefix, hashMap);
            }
            element = element2.getParent();
        }
        addNamespaceForElement(element2, XMLConstants.XML_NS_URI, "xml", hashMap);
        return hashMap.values().iterator();
    }
}
