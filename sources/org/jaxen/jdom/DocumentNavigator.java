package org.jaxen.jdom;

import java.util.HashMap;
import java.util.Iterator;
import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenConstants;
import org.jaxen.NamedAccessNavigator;
import org.jaxen.Navigator;
import org.jaxen.XPath;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.util.SingleObjectIterator;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;

public class DocumentNavigator extends DefaultNavigator implements NamedAccessNavigator {
    private static final long serialVersionUID = -1636727587303584165L;

    private static class Singleton {
        /* access modifiers changed from: private */
        public static DocumentNavigator instance = new DocumentNavigator();

        private Singleton() {
        }
    }

    public static Navigator getInstance() {
        return Singleton.instance;
    }

    public boolean isElement(Object obj) {
        return obj instanceof Element;
    }

    public boolean isComment(Object obj) {
        return obj instanceof Comment;
    }

    public boolean isText(Object obj) {
        return (obj instanceof Text) || (obj instanceof CDATA);
    }

    public boolean isAttribute(Object obj) {
        return obj instanceof Attribute;
    }

    public boolean isProcessingInstruction(Object obj) {
        return obj instanceof ProcessingInstruction;
    }

    public boolean isDocument(Object obj) {
        return obj instanceof Document;
    }

    public boolean isNamespace(Object obj) {
        return (obj instanceof Namespace) || (obj instanceof XPathNamespace);
    }

    public String getElementName(Object obj) {
        return ((Element) obj).getName();
    }

    public String getElementNamespaceUri(Object obj) {
        String namespaceURI = ((Element) obj).getNamespaceURI();
        if (namespaceURI == null || namespaceURI.length() != 0) {
            return namespaceURI;
        }
        return null;
    }

    public String getAttributeName(Object obj) {
        return ((Attribute) obj).getName();
    }

    public String getAttributeNamespaceUri(Object obj) {
        String namespaceURI = ((Attribute) obj).getNamespaceURI();
        if (namespaceURI == null || namespaceURI.length() != 0) {
            return namespaceURI;
        }
        return null;
    }

    public Iterator getChildAxisIterator(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getContent().iterator();
        }
        if (obj instanceof Document) {
            return ((Document) obj).getContent().iterator();
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getChildAxisIterator(Object obj, String str, String str2, String str3) {
        if (obj instanceof Element) {
            Element element = (Element) obj;
            if (str3 == null) {
                return element.getChildren(str).iterator();
            }
            return element.getChildren(str, Namespace.getNamespace(str2, str3)).iterator();
        } else if (!(obj instanceof Document)) {
            return JaxenConstants.EMPTY_ITERATOR;
        } else {
            Element rootElement = ((Document) obj).getRootElement();
            if (!rootElement.getName().equals(str)) {
                return JaxenConstants.EMPTY_ITERATOR;
            }
            if (str3 != null) {
                if (!Namespace.getNamespace(str2, str3).equals(rootElement.getNamespace())) {
                    return JaxenConstants.EMPTY_ITERATOR;
                }
            } else if (rootElement.getNamespace() != Namespace.NO_NAMESPACE) {
                return JaxenConstants.EMPTY_ITERATOR;
            }
            return new SingleObjectIterator(rootElement);
        }
    }

    public Iterator getNamespaceAxisIterator(Object obj) {
        if (!(obj instanceof Element)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        Element element = (Element) obj;
        HashMap hashMap = new HashMap();
        for (Element element2 = element; element2 != null; element2 = element2.getParent() instanceof Element ? (Element) element2.getParent() : null) {
            Namespace namespace = element2.getNamespace();
            if (namespace != Namespace.NO_NAMESPACE && !hashMap.containsKey(namespace.getPrefix())) {
                hashMap.put(namespace.getPrefix(), new XPathNamespace(element, namespace));
            }
            for (Namespace namespace2 : element2.getAdditionalNamespaces()) {
                if (!hashMap.containsKey(namespace2.getPrefix())) {
                    hashMap.put(namespace2.getPrefix(), new XPathNamespace(element, namespace2));
                }
            }
            for (Attribute namespace3 : element2.getAttributes()) {
                Namespace namespace4 = namespace3.getNamespace();
                if (namespace4 != Namespace.NO_NAMESPACE && !hashMap.containsKey(namespace4.getPrefix())) {
                    hashMap.put(namespace4.getPrefix(), new XPathNamespace(element, namespace4));
                }
            }
        }
        hashMap.put("xml", new XPathNamespace(element, Namespace.XML_NAMESPACE));
        return hashMap.values().iterator();
    }

    public Iterator getParentAxisIterator(Object obj) {
        Document document;
        if (obj instanceof Document) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        if (obj instanceof Element) {
            Element element = (Element) obj;
            document = element.getParent();
            if (document == null && element.isRootElement()) {
                document = element.getDocument();
            }
        } else {
            document = obj instanceof Attribute ? ((Attribute) obj).getParent() : obj instanceof XPathNamespace ? ((XPathNamespace) obj).getJDOMElement() : obj instanceof ProcessingInstruction ? ((ProcessingInstruction) obj).getParent() : obj instanceof Comment ? ((Comment) obj).getParent() : obj instanceof Text ? ((Text) obj).getParent() : null;
        }
        if (document != null) {
            return new SingleObjectIterator(document);
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getAttributeAxisIterator(Object obj) {
        if (!(obj instanceof Element)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        return ((Element) obj).getAttributes().iterator();
    }

    public Iterator getAttributeAxisIterator(Object obj, String str, String str2, String str3) {
        if (obj instanceof Element) {
            Attribute attribute = ((Element) obj).getAttribute(str, str3 == null ? Namespace.NO_NAMESPACE : Namespace.getNamespace(str2, str3));
            if (attribute != null) {
                return new SingleObjectIterator(attribute);
            }
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public XPath parseXPath(String str) throws SAXPathException {
        return new JDOMXPath(str);
    }

    public Object getDocumentNode(Object obj) {
        if (obj instanceof Document) {
            return obj;
        }
        return ((Element) obj).getDocument();
    }

    public String getElementQName(Object obj) {
        Element element = (Element) obj;
        String namespacePrefix = element.getNamespacePrefix();
        if (namespacePrefix == null || namespacePrefix.length() == 0) {
            return element.getName();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(namespacePrefix);
        stringBuffer.append(":");
        stringBuffer.append(element.getName());
        return stringBuffer.toString();
    }

    public String getAttributeQName(Object obj) {
        Attribute attribute = (Attribute) obj;
        String namespacePrefix = attribute.getNamespacePrefix();
        if (namespacePrefix == null || "".equals(namespacePrefix)) {
            return attribute.getName();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(namespacePrefix);
        stringBuffer.append(":");
        stringBuffer.append(attribute.getName());
        return stringBuffer.toString();
    }

    public String getNamespaceStringValue(Object obj) {
        if (obj instanceof Namespace) {
            return ((Namespace) obj).getURI();
        }
        return ((XPathNamespace) obj).getJDOMNamespace().getURI();
    }

    public String getNamespacePrefix(Object obj) {
        if (obj instanceof Namespace) {
            return ((Namespace) obj).getPrefix();
        }
        return ((XPathNamespace) obj).getJDOMNamespace().getPrefix();
    }

    public String getTextStringValue(Object obj) {
        if (obj instanceof Text) {
            return ((Text) obj).getText();
        }
        return obj instanceof CDATA ? ((CDATA) obj).getText() : "";
    }

    public String getAttributeStringValue(Object obj) {
        return ((Attribute) obj).getValue();
    }

    public String getElementStringValue(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Object next : ((Element) obj).getContent()) {
            if (next instanceof Text) {
                stringBuffer.append(((Text) next).getText());
            } else if (next instanceof CDATA) {
                stringBuffer.append(((CDATA) next).getText());
            } else if (next instanceof Element) {
                stringBuffer.append(getElementStringValue(next));
            }
        }
        return stringBuffer.toString();
    }

    public String getProcessingInstructionTarget(Object obj) {
        return ((ProcessingInstruction) obj).getTarget();
    }

    public String getProcessingInstructionData(Object obj) {
        return ((ProcessingInstruction) obj).getData();
    }

    public String getCommentStringValue(Object obj) {
        return ((Comment) obj).getText();
    }

    public String translateNamespacePrefixToUri(String str, Object obj) {
        Element element;
        Namespace namespace;
        if (obj instanceof Element) {
            element = (Element) obj;
        } else if (obj instanceof Text) {
            element = (Element) ((Text) obj).getParent();
        } else if (obj instanceof Attribute) {
            element = ((Attribute) obj).getParent();
        } else if (obj instanceof XPathNamespace) {
            element = ((XPathNamespace) obj).getJDOMElement();
        } else if (obj instanceof Comment) {
            element = (Element) ((Comment) obj).getParent();
        } else {
            element = obj instanceof ProcessingInstruction ? (Element) ((ProcessingInstruction) obj).getParent() : null;
        }
        if (element == null || (namespace = element.getNamespace(str)) == null) {
            return null;
        }
        return namespace.getURI();
    }

    public Object getDocument(String str) throws FunctionCallException {
        try {
            return new SAXBuilder().build(str);
        } catch (Exception e) {
            throw new FunctionCallException(e.getMessage());
        }
    }
}
