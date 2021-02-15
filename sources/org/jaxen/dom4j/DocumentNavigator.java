package org.jaxen.dom4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenConstants;
import org.jaxen.NamedAccessNavigator;
import org.jaxen.Navigator;
import org.jaxen.XPath;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.util.SingleObjectIterator;

public class DocumentNavigator extends DefaultNavigator implements NamedAccessNavigator {
    private static final long serialVersionUID = 5582300797286535936L;
    private transient SAXReader reader;

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
        return obj instanceof Namespace;
    }

    public String getElementName(Object obj) {
        return ((Element) obj).getName();
    }

    public String getElementNamespaceUri(Object obj) {
        String namespaceURI = ((Element) obj).getNamespaceURI();
        return namespaceURI == null ? "" : namespaceURI;
    }

    public String getElementQName(Object obj) {
        return ((Element) obj).getQualifiedName();
    }

    public String getAttributeName(Object obj) {
        return ((Attribute) obj).getName();
    }

    public String getAttributeNamespaceUri(Object obj) {
        String namespaceURI = ((Attribute) obj).getNamespaceURI();
        return namespaceURI == null ? "" : namespaceURI;
    }

    public String getAttributeQName(Object obj) {
        return ((Attribute) obj).getQualifiedName();
    }

    public Iterator getChildAxisIterator(Object obj) {
        Iterator nodeIterator = obj instanceof Branch ? ((Branch) obj).nodeIterator() : null;
        if (nodeIterator != null) {
            return nodeIterator;
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getChildAxisIterator(Object obj, String str, String str2, String str3) {
        if (obj instanceof Element) {
            return ((Element) obj).elementIterator(QName.get(str, str2, str3));
        }
        if (!(obj instanceof Document)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        Element rootElement = ((Document) obj).getRootElement();
        if (rootElement == null || !rootElement.getName().equals(str)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        if (str3 == null || str3.equals(rootElement.getNamespaceURI())) {
            return new SingleObjectIterator(rootElement);
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getParentAxisIterator(Object obj) {
        if (obj instanceof Document) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        Node node = (Node) obj;
        Object parent = node.getParent();
        if (parent == null) {
            parent = node.getDocument();
        }
        return new SingleObjectIterator(parent);
    }

    public Iterator getAttributeAxisIterator(Object obj) {
        if (!(obj instanceof Element)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        return ((Element) obj).attributeIterator();
    }

    public Iterator getAttributeAxisIterator(Object obj, String str, String str2, String str3) {
        if (!(obj instanceof Element)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        Attribute attribute = ((Element) obj).attribute(QName.get(str, str2, str3));
        if (attribute == null) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        return new SingleObjectIterator(attribute);
    }

    public Iterator getNamespaceAxisIterator(Object obj) {
        if (!(obj instanceof Element)) {
            return JaxenConstants.EMPTY_ITERATOR;
        }
        Element element = (Element) obj;
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (Element element2 = element; element2 != null; element2 = element2.getParent()) {
            ArrayList<Namespace> arrayList2 = new ArrayList<>(element2.declaredNamespaces());
            arrayList2.add(element2.getNamespace());
            for (Attribute namespace : element2.attributes()) {
                arrayList2.add(namespace.getNamespace());
            }
            for (Namespace namespace2 : arrayList2) {
                if (namespace2 != Namespace.NO_NAMESPACE) {
                    String prefix = namespace2.getPrefix();
                    if (!hashSet.contains(prefix)) {
                        hashSet.add(prefix);
                        arrayList.add(namespace2.asXPathResult(element));
                    }
                }
            }
        }
        arrayList.add(Namespace.XML_NAMESPACE.asXPathResult(element));
        return arrayList.iterator();
    }

    public Object getDocumentNode(Object obj) {
        if (obj instanceof Document) {
            return obj;
        }
        if (obj instanceof Node) {
            return ((Node) obj).getDocument();
        }
        return null;
    }

    public XPath parseXPath(String str) throws SAXPathException {
        return new Dom4jXPath(str);
    }

    public Object getParentNode(Object obj) {
        if (!(obj instanceof Node)) {
            return null;
        }
        Node node = (Node) obj;
        Object parent = node.getParent();
        if (parent == null && (parent = node.getDocument()) == obj) {
            return null;
        }
        return parent;
    }

    public String getTextStringValue(Object obj) {
        return getNodeStringValue((Node) obj);
    }

    public String getElementStringValue(Object obj) {
        return getNodeStringValue((Node) obj);
    }

    public String getAttributeStringValue(Object obj) {
        return getNodeStringValue((Node) obj);
    }

    private String getNodeStringValue(Node node) {
        return node.getStringValue();
    }

    public String getNamespaceStringValue(Object obj) {
        return ((Namespace) obj).getURI();
    }

    public String getNamespacePrefix(Object obj) {
        return ((Namespace) obj).getPrefix();
    }

    public String getCommentStringValue(Object obj) {
        return ((Comment) obj).getText();
    }

    public String translateNamespacePrefixToUri(String str, Object obj) {
        Element element;
        Namespace namespaceForPrefix;
        if (obj instanceof Element) {
            element = (Element) obj;
        } else {
            element = obj instanceof Node ? ((Node) obj).getParent() : null;
        }
        if (element == null || (namespaceForPrefix = element.getNamespaceForPrefix(str)) == null) {
            return null;
        }
        return namespaceForPrefix.getURI();
    }

    public short getNodeType(Object obj) {
        if (obj instanceof Node) {
            return ((Node) obj).getNodeType();
        }
        return 0;
    }

    public Object getDocument(String str) throws FunctionCallException {
        try {
            return getSAXReader().read(str);
        } catch (DocumentException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to parse document for URI: ");
            stringBuffer.append(str);
            throw new FunctionCallException(stringBuffer.toString(), e);
        }
    }

    public String getProcessingInstructionTarget(Object obj) {
        return ((ProcessingInstruction) obj).getTarget();
    }

    public String getProcessingInstructionData(Object obj) {
        return ((ProcessingInstruction) obj).getText();
    }

    public SAXReader getSAXReader() {
        if (this.reader == null) {
            SAXReader sAXReader = new SAXReader();
            this.reader = sAXReader;
            sAXReader.setMergeAdjacentText(true);
        }
        return this.reader;
    }

    public void setSAXReader(SAXReader sAXReader) {
        this.reader = sAXReader;
    }
}
