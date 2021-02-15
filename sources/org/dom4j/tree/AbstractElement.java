package org.dom4j.tree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.CharacterData;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;

public abstract class AbstractElement extends AbstractBranch implements Element {
    private static final DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
    protected static final Iterator EMPTY_ITERATOR;
    protected static final List EMPTY_LIST;
    protected static final boolean USE_STRINGVALUE_SEPARATOR = false;
    protected static final boolean VERBOSE_TOSTRING = false;

    /* access modifiers changed from: protected */
    public abstract List attributeList();

    /* access modifiers changed from: protected */
    public abstract List attributeList(int i);

    public short getNodeType() {
        return 1;
    }

    public void setData(Object obj) {
    }

    static {
        List list = Collections.EMPTY_LIST;
        EMPTY_LIST = list;
        EMPTY_ITERATOR = list.iterator();
    }

    public boolean isRootElement() {
        Document document = getDocument();
        return document != null && document.getRootElement() == this;
    }

    public void setName(String str) {
        setQName(getDocumentFactory().createQName(str));
    }

    public void setNamespace(Namespace namespace) {
        setQName(getDocumentFactory().createQName(getName(), namespace));
    }

    public String getXPathNameStep() {
        String namespaceURI = getNamespaceURI();
        if (namespaceURI == null || namespaceURI.length() == 0) {
            return getName();
        }
        String namespacePrefix = getNamespacePrefix();
        if (namespacePrefix != null && namespacePrefix.length() != 0) {
            return getQualifiedName();
        }
        StringBuffer stringBuffer = new StringBuffer("*[name()='");
        stringBuffer.append(getName());
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

    public String getPath(Element element) {
        if (this == element) {
            return ".";
        }
        Element parent = getParent();
        if (parent == null) {
            StringBuffer stringBuffer = new StringBuffer("/");
            stringBuffer.append(getXPathNameStep());
            return stringBuffer.toString();
        } else if (parent == element) {
            return getXPathNameStep();
        } else {
            StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(parent.getPath(element)));
            stringBuffer2.append("/");
            stringBuffer2.append(getXPathNameStep());
            return stringBuffer2.toString();
        }
    }

    public String getUniquePath(Element element) {
        int indexOf;
        Element parent = getParent();
        if (parent == null) {
            StringBuffer stringBuffer = new StringBuffer("/");
            stringBuffer.append(getXPathNameStep());
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        if (parent != element) {
            stringBuffer2.append(parent.getUniquePath(element));
            stringBuffer2.append("/");
        }
        stringBuffer2.append(getXPathNameStep());
        List elements = parent.elements(getQName());
        if (elements.size() > 1 && (indexOf = elements.indexOf(this)) >= 0) {
            stringBuffer2.append("[");
            stringBuffer2.append(Integer.toString(indexOf + 1));
            stringBuffer2.append("]");
        }
        return stringBuffer2.toString();
    }

    public String asXML() {
        try {
            StringWriter stringWriter = new StringWriter();
            XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter, new OutputFormat());
            xMLWriter.write((Element) this);
            xMLWriter.flush();
            return stringWriter.toString();
        } catch (IOException e) {
            StringBuffer stringBuffer = new StringBuffer("IOException while generating textual representation: ");
            stringBuffer.append(e.getMessage());
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    public void write(Writer writer) throws IOException {
        new XMLWriter(writer, new OutputFormat()).write((Element) this);
    }

    public void accept(Visitor visitor) {
        visitor.visit((Element) this);
        int attributeCount = attributeCount();
        for (int i = 0; i < attributeCount; i++) {
            visitor.visit(attribute(i));
        }
        int nodeCount = nodeCount();
        for (int i2 = 0; i2 < nodeCount; i2++) {
            node(i2).accept(visitor);
        }
    }

    public String toString() {
        String namespaceURI = getNamespaceURI();
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
            stringBuffer.append(" [Element: <");
            stringBuffer.append(getQualifiedName());
            stringBuffer.append(" attributes: ");
            stringBuffer.append(attributeList());
            stringBuffer.append("/>]");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer2.append(" [Element: <");
        stringBuffer2.append(getQualifiedName());
        stringBuffer2.append(" uri: ");
        stringBuffer2.append(namespaceURI);
        stringBuffer2.append(" attributes: ");
        stringBuffer2.append(attributeList());
        stringBuffer2.append("/>]");
        return stringBuffer2.toString();
    }

    public Namespace getNamespace() {
        return getQName().getNamespace();
    }

    public String getName() {
        return getQName().getName();
    }

    public String getNamespacePrefix() {
        return getQName().getNamespacePrefix();
    }

    public String getNamespaceURI() {
        return getQName().getNamespaceURI();
    }

    public String getQualifiedName() {
        return getQName().getQualifiedName();
    }

    public Object getData() {
        return getText();
    }

    public Node node(int i) {
        Object obj;
        if (i >= 0) {
            List contentList = contentList();
            if (i < contentList.size() && (obj = contentList.get(i)) != null) {
                if (obj instanceof Node) {
                    return (Node) obj;
                }
                return getDocumentFactory().createText(obj.toString());
            }
        }
        return null;
    }

    public int indexOf(Node node) {
        return contentList().indexOf(node);
    }

    public int nodeCount() {
        return contentList().size();
    }

    public Iterator nodeIterator() {
        return contentList().iterator();
    }

    public Element element(String str) {
        List contentList = contentList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Element) {
                Element element = (Element) obj;
                if (str.equals(element.getName())) {
                    return element;
                }
            }
        }
        return null;
    }

    public Element element(QName qName) {
        List contentList = contentList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Element) {
                Element element = (Element) obj;
                if (qName.equals(element.getQName())) {
                    return element;
                }
            }
        }
        return null;
    }

    public Element element(String str, Namespace namespace) {
        return element(getDocumentFactory().createQName(str, namespace));
    }

    public List elements() {
        List contentList = contentList();
        BackedList createResultList = createResultList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Element) {
                createResultList.addLocal(obj);
            }
        }
        return createResultList;
    }

    public List elements(String str) {
        List contentList = contentList();
        BackedList createResultList = createResultList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Element) {
                Element element = (Element) obj;
                if (str.equals(element.getName())) {
                    createResultList.addLocal(element);
                }
            }
        }
        return createResultList;
    }

    public List elements(QName qName) {
        List contentList = contentList();
        BackedList createResultList = createResultList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Element) {
                Element element = (Element) obj;
                if (qName.equals(element.getQName())) {
                    createResultList.addLocal(element);
                }
            }
        }
        return createResultList;
    }

    public List elements(String str, Namespace namespace) {
        return elements(getDocumentFactory().createQName(str, namespace));
    }

    public Iterator elementIterator() {
        return elements().iterator();
    }

    public Iterator elementIterator(String str) {
        return elements(str).iterator();
    }

    public Iterator elementIterator(QName qName) {
        return elements(qName).iterator();
    }

    public Iterator elementIterator(String str, Namespace namespace) {
        return elementIterator(getDocumentFactory().createQName(str, namespace));
    }

    public List attributes() {
        return new ContentListFacade(this, attributeList());
    }

    public Iterator attributeIterator() {
        return attributeList().iterator();
    }

    public Attribute attribute(int i) {
        return (Attribute) attributeList().get(i);
    }

    public int attributeCount() {
        return attributeList().size();
    }

    public Attribute attribute(String str) {
        List attributeList = attributeList();
        int size = attributeList.size();
        for (int i = 0; i < size; i++) {
            Attribute attribute = (Attribute) attributeList.get(i);
            if (str.equals(attribute.getName())) {
                return attribute;
            }
        }
        return null;
    }

    public Attribute attribute(QName qName) {
        List attributeList = attributeList();
        int size = attributeList.size();
        for (int i = 0; i < size; i++) {
            Attribute attribute = (Attribute) attributeList.get(i);
            if (qName.equals(attribute.getQName())) {
                return attribute;
            }
        }
        return null;
    }

    public Attribute attribute(String str, Namespace namespace) {
        return attribute(getDocumentFactory().createQName(str, namespace));
    }

    public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean z) {
        int length = attributes.getLength();
        if (length > 0) {
            DocumentFactory documentFactory = getDocumentFactory();
            if (length == 1) {
                String qName = attributes.getQName(0);
                if (z || !qName.startsWith(XMLConstants.XMLNS_ATTRIBUTE)) {
                    String uri = attributes.getURI(0);
                    String localName = attributes.getLocalName(0);
                    add(documentFactory.createAttribute((Element) this, namespaceStack.getAttributeQName(uri, localName, qName), attributes.getValue(0)));
                    return;
                }
                return;
            }
            List attributeList = attributeList(length);
            attributeList.clear();
            for (int i = 0; i < length; i++) {
                String qName2 = attributes.getQName(i);
                if (z || !qName2.startsWith(XMLConstants.XMLNS_ATTRIBUTE)) {
                    String uri2 = attributes.getURI(i);
                    String localName2 = attributes.getLocalName(i);
                    Attribute createAttribute = documentFactory.createAttribute((Element) this, namespaceStack.getAttributeQName(uri2, localName2, qName2), attributes.getValue(i));
                    attributeList.add(createAttribute);
                    childAdded(createAttribute);
                }
            }
        }
    }

    public String attributeValue(String str) {
        Attribute attribute = attribute(str);
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    public String attributeValue(QName qName) {
        Attribute attribute = attribute(qName);
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    public String attributeValue(String str, String str2) {
        String attributeValue = attributeValue(str);
        return attributeValue != null ? attributeValue : str2;
    }

    public String attributeValue(QName qName, String str) {
        String attributeValue = attributeValue(qName);
        return attributeValue != null ? attributeValue : str;
    }

    public void setAttributeValue(String str, String str2) {
        addAttribute(str, str2);
    }

    public void setAttributeValue(QName qName, String str) {
        addAttribute(qName, str);
    }

    public void add(Attribute attribute) {
        if (attribute.getParent() != null) {
            StringBuffer stringBuffer = new StringBuffer("The Attribute already has an existing parent \"");
            stringBuffer.append(attribute.getParent().getQualifiedName());
            stringBuffer.append("\"");
            throw new IllegalAddException((Element) this, (Node) attribute, stringBuffer.toString());
        } else if (attribute.getValue() == null) {
            Attribute attribute2 = attribute(attribute.getQName());
            if (attribute2 != null) {
                remove(attribute2);
            }
        } else {
            attributeList().add(attribute);
            childAdded(attribute);
        }
    }

    public boolean remove(Attribute attribute) {
        List attributeList = attributeList();
        boolean remove = attributeList.remove(attribute);
        if (remove) {
            childRemoved(attribute);
            return remove;
        }
        Attribute attribute2 = attribute(attribute.getQName());
        if (attribute2 == null) {
            return remove;
        }
        attributeList.remove(attribute2);
        return true;
    }

    public List processingInstructions() {
        List contentList = contentList();
        BackedList createResultList = createResultList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof ProcessingInstruction) {
                createResultList.addLocal(obj);
            }
        }
        return createResultList;
    }

    public List processingInstructions(String str) {
        List contentList = contentList();
        BackedList createResultList = createResultList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof ProcessingInstruction) {
                ProcessingInstruction processingInstruction = (ProcessingInstruction) obj;
                if (str.equals(processingInstruction.getName())) {
                    createResultList.addLocal(processingInstruction);
                }
            }
        }
        return createResultList;
    }

    public ProcessingInstruction processingInstruction(String str) {
        List contentList = contentList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof ProcessingInstruction) {
                ProcessingInstruction processingInstruction = (ProcessingInstruction) obj;
                if (str.equals(processingInstruction.getName())) {
                    return processingInstruction;
                }
            }
        }
        return null;
    }

    public boolean removeProcessingInstruction(String str) {
        Iterator it = contentList().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if ((next instanceof ProcessingInstruction) && str.equals(((ProcessingInstruction) next).getName())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public Node getXPathResult(int i) {
        Node node = node(i);
        return (node == null || node.supportsParent()) ? node : node.asXPathResult(this);
    }

    public Element addAttribute(String str, String str2) {
        Attribute attribute = attribute(str);
        if (str2 != null) {
            if (attribute == null) {
                add(getDocumentFactory().createAttribute((Element) this, str, str2));
            } else if (attribute.isReadOnly()) {
                remove(attribute);
                add(getDocumentFactory().createAttribute((Element) this, str, str2));
            } else {
                attribute.setValue(str2);
            }
        } else if (attribute != null) {
            remove(attribute);
        }
        return this;
    }

    public Element addAttribute(QName qName, String str) {
        Attribute attribute = attribute(qName);
        if (str != null) {
            if (attribute == null) {
                add(getDocumentFactory().createAttribute((Element) this, qName, str));
            } else if (attribute.isReadOnly()) {
                remove(attribute);
                add(getDocumentFactory().createAttribute((Element) this, qName, str));
            } else {
                attribute.setValue(str);
            }
        } else if (attribute != null) {
            remove(attribute);
        }
        return this;
    }

    public Element addCDATA(String str) {
        addNewNode(getDocumentFactory().createCDATA(str));
        return this;
    }

    public Element addComment(String str) {
        addNewNode(getDocumentFactory().createComment(str));
        return this;
    }

    public Element addElement(String str) {
        Namespace namespace;
        String str2;
        Element element;
        DocumentFactory documentFactory = getDocumentFactory();
        int indexOf = str.indexOf(":");
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            str2 = str.substring(indexOf + 1);
            namespace = getNamespaceForPrefix(substring);
            if (namespace == null) {
                StringBuffer stringBuffer = new StringBuffer("No such namespace prefix: ");
                stringBuffer.append(substring);
                stringBuffer.append(" is in scope on: ");
                stringBuffer.append(this);
                stringBuffer.append(" so cannot add element: ");
                stringBuffer.append(str);
                throw new IllegalAddException(stringBuffer.toString());
            }
        } else {
            namespace = getNamespaceForPrefix("");
            str2 = str;
        }
        if (namespace != null) {
            element = documentFactory.createElement(documentFactory.createQName(str2, namespace));
        } else {
            element = documentFactory.createElement(str);
        }
        addNewNode(element);
        return element;
    }

    public Element addEntity(String str, String str2) {
        addNewNode(getDocumentFactory().createEntity(str, str2));
        return this;
    }

    public Element addNamespace(String str, String str2) {
        addNewNode(getDocumentFactory().createNamespace(str, str2));
        return this;
    }

    public Element addProcessingInstruction(String str, String str2) {
        addNewNode(getDocumentFactory().createProcessingInstruction(str, str2));
        return this;
    }

    public Element addProcessingInstruction(String str, Map map) {
        addNewNode(getDocumentFactory().createProcessingInstruction(str, map));
        return this;
    }

    public Element addText(String str) {
        addNewNode(getDocumentFactory().createText(str));
        return this;
    }

    public void add(Node node) {
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            add((Element) node);
        } else if (nodeType == 2) {
            add((Attribute) node);
        } else if (nodeType == 3) {
            add((Text) node);
        } else if (nodeType == 4) {
            add((CDATA) node);
        } else if (nodeType == 5) {
            add((Entity) node);
        } else if (nodeType == 7) {
            add((ProcessingInstruction) node);
        } else if (nodeType == 8) {
            add((Comment) node);
        } else if (nodeType != 13) {
            invalidNodeTypeAddException(node);
        } else {
            add((Namespace) node);
        }
    }

    public boolean remove(Node node) {
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            return remove((Element) node);
        }
        if (nodeType == 2) {
            return remove((Attribute) node);
        }
        if (nodeType == 3) {
            return remove((Text) node);
        }
        if (nodeType == 4) {
            return remove((CDATA) node);
        }
        if (nodeType == 5) {
            return remove((Entity) node);
        }
        if (nodeType == 7) {
            return remove((ProcessingInstruction) node);
        }
        if (nodeType == 8) {
            return remove((Comment) node);
        }
        if (nodeType != 13) {
            return false;
        }
        return remove((Namespace) node);
    }

    public void add(CDATA cdata) {
        addNode(cdata);
    }

    public void add(Comment comment) {
        addNode(comment);
    }

    public void add(Element element) {
        addNode(element);
    }

    public void add(Entity entity) {
        addNode(entity);
    }

    public void add(Namespace namespace) {
        addNode(namespace);
    }

    public void add(ProcessingInstruction processingInstruction) {
        addNode(processingInstruction);
    }

    public void add(Text text) {
        addNode(text);
    }

    public boolean remove(CDATA cdata) {
        return removeNode(cdata);
    }

    public boolean remove(Comment comment) {
        return removeNode(comment);
    }

    public boolean remove(Element element) {
        return removeNode(element);
    }

    public boolean remove(Entity entity) {
        return removeNode(entity);
    }

    public boolean remove(Namespace namespace) {
        return removeNode(namespace);
    }

    public boolean remove(ProcessingInstruction processingInstruction) {
        return removeNode(processingInstruction);
    }

    public boolean remove(Text text) {
        return removeNode(text);
    }

    public boolean hasMixedContent() {
        List<Object> contentList = contentList();
        if (contentList == null || contentList.isEmpty() || contentList.size() < 2) {
            return false;
        }
        Class<?> cls = null;
        for (Object obj : contentList) {
            Class<?> cls2 = obj.getClass();
            if (cls2 != cls) {
                if (cls != null) {
                    return true;
                }
                cls = cls2;
            }
        }
        return false;
    }

    public boolean isTextOnly() {
        List contentList = contentList();
        if (contentList == null || contentList.isEmpty()) {
            return true;
        }
        for (Object next : contentList) {
            if (!(next instanceof CharacterData) && !(next instanceof String)) {
                return false;
            }
        }
        return true;
    }

    public void setText(String str) {
        List contentList = contentList();
        if (contentList != null) {
            Iterator it = contentList.iterator();
            while (it.hasNext()) {
                short nodeType = ((Node) it.next()).getNodeType();
                if (nodeType == 3 || nodeType == 4 || nodeType == 5) {
                    it.remove();
                }
            }
        }
        addText(str);
    }

    public String getStringValue() {
        List contentList = contentList();
        int size = contentList.size();
        if (size <= 0) {
            return "";
        }
        if (size == 1) {
            return getContentAsStringValue(contentList.get(0));
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            String contentAsStringValue = getContentAsStringValue(contentList.get(i));
            if (contentAsStringValue.length() > 0) {
                stringBuffer.append(contentAsStringValue);
            }
        }
        return stringBuffer.toString();
    }

    public void normalize() {
        List contentList = contentList();
        int i = 0;
        while (true) {
            Text text = null;
            while (i < contentList.size()) {
                Node node = (Node) contentList.get(i);
                if (node instanceof Text) {
                    Text text2 = (Text) node;
                    if (text != null) {
                        text.appendText(text2.getText());
                        remove(text2);
                    } else {
                        String text3 = text2.getText();
                        if (text3 == null || text3.length() <= 0) {
                            remove(text2);
                        } else {
                            i++;
                            text = text2;
                        }
                    }
                } else {
                    if (node instanceof Element) {
                        ((Element) node).normalize();
                    }
                    i++;
                }
            }
            return;
        }
    }

    public String elementText(String str) {
        Element element = element(str);
        if (element != null) {
            return element.getText();
        }
        return null;
    }

    public String elementText(QName qName) {
        Element element = element(qName);
        if (element != null) {
            return element.getText();
        }
        return null;
    }

    public String elementTextTrim(String str) {
        Element element = element(str);
        if (element != null) {
            return element.getTextTrim();
        }
        return null;
    }

    public String elementTextTrim(QName qName) {
        Element element = element(qName);
        if (element != null) {
            return element.getTextTrim();
        }
        return null;
    }

    public void appendAttributes(Element element) {
        int attributeCount = element.attributeCount();
        for (int i = 0; i < attributeCount; i++) {
            Attribute attribute = element.attribute(i);
            if (attribute.supportsParent()) {
                addAttribute(attribute.getQName(), attribute.getValue());
            } else {
                add(attribute);
            }
        }
    }

    public Element createCopy() {
        Element createElement = createElement(getQName());
        createElement.appendAttributes(this);
        createElement.appendContent(this);
        return createElement;
    }

    public Element createCopy(String str) {
        Element createElement = createElement(str);
        createElement.appendAttributes(this);
        createElement.appendContent(this);
        return createElement;
    }

    public Element createCopy(QName qName) {
        Element createElement = createElement(qName);
        createElement.appendAttributes(this);
        createElement.appendContent(this);
        return createElement;
    }

    public QName getQName(String str) {
        String str2;
        int indexOf = str.indexOf(":");
        if (indexOf > 0) {
            str2 = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        } else {
            str2 = "";
        }
        Namespace namespaceForPrefix = getNamespaceForPrefix(str2);
        if (namespaceForPrefix != null) {
            return getDocumentFactory().createQName(str, namespaceForPrefix);
        }
        return getDocumentFactory().createQName(str);
    }

    public Namespace getNamespaceForPrefix(String str) {
        Namespace namespaceForPrefix;
        if (str == null) {
            str = "";
        }
        if (str.equals(getNamespacePrefix())) {
            return getNamespace();
        }
        if (str.equals("xml")) {
            return Namespace.XML_NAMESPACE;
        }
        List contentList = contentList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Namespace) {
                Namespace namespace = (Namespace) obj;
                if (str.equals(namespace.getPrefix())) {
                    return namespace;
                }
            }
        }
        Element parent = getParent();
        if (parent != null && (namespaceForPrefix = parent.getNamespaceForPrefix(str)) != null) {
            return namespaceForPrefix;
        }
        if (str == null || str.length() <= 0) {
            return Namespace.NO_NAMESPACE;
        }
        return null;
    }

    public Namespace getNamespaceForURI(String str) {
        if (str == null || str.length() <= 0) {
            return Namespace.NO_NAMESPACE;
        }
        if (str.equals(getNamespaceURI())) {
            return getNamespace();
        }
        List contentList = contentList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Namespace) {
                Namespace namespace = (Namespace) obj;
                if (str.equals(namespace.getURI())) {
                    return namespace;
                }
            }
        }
        return null;
    }

    public List getNamespacesForURI(String str) {
        BackedList createResultList = createResultList();
        List contentList = contentList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if ((obj instanceof Namespace) && ((Namespace) obj).getURI().equals(str)) {
                createResultList.addLocal(obj);
            }
        }
        return createResultList;
    }

    public List declaredNamespaces() {
        BackedList createResultList = createResultList();
        List contentList = contentList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Namespace) {
                createResultList.addLocal(obj);
            }
        }
        return createResultList;
    }

    public List additionalNamespaces() {
        List contentList = contentList();
        int size = contentList.size();
        BackedList createResultList = createResultList();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Namespace) {
                Namespace namespace = (Namespace) obj;
                if (!namespace.equals(getNamespace())) {
                    createResultList.addLocal(namespace);
                }
            }
        }
        return createResultList;
    }

    public List additionalNamespaces(String str) {
        List contentList = contentList();
        BackedList createResultList = createResultList();
        int size = contentList.size();
        for (int i = 0; i < size; i++) {
            Object obj = contentList.get(i);
            if (obj instanceof Namespace) {
                Namespace namespace = (Namespace) obj;
                if (!str.equals(namespace.getURI())) {
                    createResultList.addLocal(namespace);
                }
            }
        }
        return createResultList;
    }

    public void ensureAttributesCapacity(int i) {
        if (i > 1) {
            List attributeList = attributeList();
            if (attributeList instanceof ArrayList) {
                ((ArrayList) attributeList).ensureCapacity(i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Element createElement(String str) {
        return getDocumentFactory().createElement(str);
    }

    /* access modifiers changed from: protected */
    public Element createElement(QName qName) {
        return getDocumentFactory().createElement(qName);
    }

    /* access modifiers changed from: protected */
    public void addNode(Node node) {
        if (node.getParent() == null) {
            addNewNode(node);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("The Node already has an existing parent of \"");
        stringBuffer.append(node.getParent().getQualifiedName());
        stringBuffer.append("\"");
        throw new IllegalAddException((Element) this, node, stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public void addNode(int i, Node node) {
        if (node.getParent() == null) {
            addNewNode(i, node);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("The Node already has an existing parent of \"");
        stringBuffer.append(node.getParent().getQualifiedName());
        stringBuffer.append("\"");
        throw new IllegalAddException((Element) this, node, stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public void addNewNode(Node node) {
        contentList().add(node);
        childAdded(node);
    }

    /* access modifiers changed from: protected */
    public void addNewNode(int i, Node node) {
        contentList().add(i, node);
        childAdded(node);
    }

    /* access modifiers changed from: protected */
    public boolean removeNode(Node node) {
        boolean remove = contentList().remove(node);
        if (remove) {
            childRemoved(node);
        }
        return remove;
    }

    /* access modifiers changed from: protected */
    public void childAdded(Node node) {
        if (node != null) {
            node.setParent(this);
        }
    }

    /* access modifiers changed from: protected */
    public void childRemoved(Node node) {
        if (node != null) {
            node.setParent((Element) null);
            node.setDocument((Document) null);
        }
    }

    /* access modifiers changed from: protected */
    public DocumentFactory getDocumentFactory() {
        DocumentFactory documentFactory;
        QName qName = getQName();
        if (qName == null || (documentFactory = qName.getDocumentFactory()) == null) {
            return DOCUMENT_FACTORY;
        }
        return documentFactory;
    }

    /* access modifiers changed from: protected */
    public List createAttributeList() {
        return createAttributeList(5);
    }

    /* access modifiers changed from: protected */
    public List createAttributeList(int i) {
        return new ArrayList(i);
    }

    /* access modifiers changed from: protected */
    public Iterator createSingleIterator(Object obj) {
        return new SingleIterator(obj);
    }
}
