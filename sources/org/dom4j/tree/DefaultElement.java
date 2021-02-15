package org.dom4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;

public class DefaultElement extends AbstractElement {
    private static final transient DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
    private Object attributes;
    private Object content;
    private Branch parentBranch;
    private QName qname;

    public boolean supportsParent() {
        return true;
    }

    public DefaultElement(String str) {
        this.qname = DOCUMENT_FACTORY.createQName(str);
    }

    public DefaultElement(QName qName) {
        this.qname = qName;
    }

    public DefaultElement(QName qName, int i) {
        this.qname = qName;
        if (i > 1) {
            this.attributes = new ArrayList(i);
        }
    }

    public DefaultElement(String str, Namespace namespace) {
        this.qname = DOCUMENT_FACTORY.createQName(str, namespace);
    }

    public Element getParent() {
        Branch branch = this.parentBranch;
        if (branch instanceof Element) {
            return (Element) branch;
        }
        return null;
    }

    public void setParent(Element element) {
        if ((this.parentBranch instanceof Element) || element != null) {
            this.parentBranch = element;
        }
    }

    public Document getDocument() {
        Branch branch = this.parentBranch;
        if (branch instanceof Document) {
            return (Document) branch;
        }
        if (branch instanceof Element) {
            return ((Element) branch).getDocument();
        }
        return null;
    }

    public void setDocument(Document document) {
        if ((this.parentBranch instanceof Document) || document != null) {
            this.parentBranch = document;
        }
    }

    public QName getQName() {
        return this.qname;
    }

    public void setQName(QName qName) {
        this.qname = qName;
    }

    public String getText() {
        Object obj = this.content;
        if (obj instanceof List) {
            return super.getText();
        }
        return obj != null ? getContentAsText(obj) : "";
    }

    public String getStringValue() {
        Object obj = this.content;
        if (!(obj instanceof List)) {
            return obj != null ? getContentAsStringValue(obj) : "";
        }
        List list = (List) obj;
        int size = list.size();
        if (size <= 0) {
            return "";
        }
        if (size == 1) {
            return getContentAsStringValue(list.get(0));
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            String contentAsStringValue = getContentAsStringValue(list.get(i));
            if (contentAsStringValue.length() > 0) {
                stringBuffer.append(contentAsStringValue);
            }
        }
        return stringBuffer.toString();
    }

    public Object clone() {
        DefaultElement defaultElement = (DefaultElement) super.clone();
        if (defaultElement != this) {
            defaultElement.content = null;
            defaultElement.attributes = null;
            defaultElement.appendAttributes(this);
            defaultElement.appendContent(this);
        }
        return defaultElement;
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
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof Namespace) {
                    Namespace namespace = (Namespace) obj2;
                    if (str.equals(namespace.getPrefix())) {
                        return namespace;
                    }
                }
            }
        } else if (obj instanceof Namespace) {
            Namespace namespace2 = (Namespace) obj;
            if (str.equals(namespace2.getPrefix())) {
                return namespace2;
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
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof Namespace) {
                    Namespace namespace = (Namespace) obj2;
                    if (str.equals(namespace.getURI())) {
                        return namespace;
                    }
                }
            }
        } else if (obj instanceof Namespace) {
            Namespace namespace2 = (Namespace) obj;
            if (str.equals(namespace2.getURI())) {
                return namespace2;
            }
        }
        Element parent = getParent();
        if (parent != null) {
            return parent.getNamespaceForURI(str);
        }
        return null;
    }

    public List declaredNamespaces() {
        BackedList createResultList = createResultList();
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof Namespace) {
                    createResultList.addLocal(obj2);
                }
            }
        } else if (obj instanceof Namespace) {
            createResultList.addLocal(obj);
        }
        return createResultList;
    }

    public List additionalNamespaces() {
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            BackedList createResultList = createResultList();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof Namespace) {
                    Namespace namespace = (Namespace) obj2;
                    if (!namespace.equals(getNamespace())) {
                        createResultList.addLocal(namespace);
                    }
                }
            }
            return createResultList;
        } else if (!(obj instanceof Namespace)) {
            return createEmptyList();
        } else {
            Namespace namespace2 = (Namespace) obj;
            if (namespace2.equals(getNamespace())) {
                return createEmptyList();
            }
            return createSingleResultList(namespace2);
        }
    }

    public List additionalNamespaces(String str) {
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            BackedList createResultList = createResultList();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof Namespace) {
                    Namespace namespace = (Namespace) obj2;
                    if (!str.equals(namespace.getURI())) {
                        createResultList.addLocal(namespace);
                    }
                }
            }
            return createResultList;
        }
        if (obj instanceof Namespace) {
            Namespace namespace2 = (Namespace) obj;
            if (!str.equals(namespace2.getURI())) {
                return createSingleResultList(namespace2);
            }
        }
        return createEmptyList();
    }

    public List processingInstructions() {
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            BackedList createResultList = createResultList();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof ProcessingInstruction) {
                    createResultList.addLocal(obj2);
                }
            }
            return createResultList;
        } else if (obj instanceof ProcessingInstruction) {
            return createSingleResultList(obj);
        } else {
            return createEmptyList();
        }
    }

    public List processingInstructions(String str) {
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            BackedList createResultList = createResultList();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof ProcessingInstruction) {
                    ProcessingInstruction processingInstruction = (ProcessingInstruction) obj2;
                    if (str.equals(processingInstruction.getName())) {
                        createResultList.addLocal(processingInstruction);
                    }
                }
            }
            return createResultList;
        }
        if (obj instanceof ProcessingInstruction) {
            ProcessingInstruction processingInstruction2 = (ProcessingInstruction) obj;
            if (str.equals(processingInstruction2.getName())) {
                return createSingleResultList(processingInstruction2);
            }
        }
        return createEmptyList();
    }

    public ProcessingInstruction processingInstruction(String str) {
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof ProcessingInstruction) {
                    ProcessingInstruction processingInstruction = (ProcessingInstruction) obj2;
                    if (str.equals(processingInstruction.getName())) {
                        return processingInstruction;
                    }
                }
            }
            return null;
        } else if (!(obj instanceof ProcessingInstruction)) {
            return null;
        } else {
            ProcessingInstruction processingInstruction2 = (ProcessingInstruction) obj;
            if (str.equals(processingInstruction2.getName())) {
                return processingInstruction2;
            }
            return null;
        }
    }

    public boolean removeProcessingInstruction(String str) {
        Object obj = this.content;
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if ((next instanceof ProcessingInstruction) && str.equals(((ProcessingInstruction) next).getName())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        } else if (!(obj instanceof ProcessingInstruction) || !str.equals(((ProcessingInstruction) obj).getName())) {
            return false;
        } else {
            this.content = null;
            return true;
        }
    }

    public Element element(String str) {
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof Element) {
                    Element element = (Element) obj2;
                    if (str.equals(element.getName())) {
                        return element;
                    }
                }
            }
            return null;
        } else if (!(obj instanceof Element)) {
            return null;
        } else {
            Element element2 = (Element) obj;
            if (str.equals(element2.getName())) {
                return element2;
            }
            return null;
        }
    }

    public Element element(QName qName) {
        Object obj = this.content;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                if (obj2 instanceof Element) {
                    Element element = (Element) obj2;
                    if (qName.equals(element.getQName())) {
                        return element;
                    }
                }
            }
            return null;
        } else if (!(obj instanceof Element)) {
            return null;
        } else {
            Element element2 = (Element) obj;
            if (qName.equals(element2.getQName())) {
                return element2;
            }
            return null;
        }
    }

    public Element element(String str, Namespace namespace) {
        return element(getDocumentFactory().createQName(str, namespace));
    }

    public void setContent(List list) {
        contentRemoved();
        if (list instanceof ContentListFacade) {
            list = ((ContentListFacade) list).getBackingList();
        }
        if (list == null) {
            this.content = null;
            return;
        }
        int size = list.size();
        List createContentList = createContentList(size);
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj instanceof Node) {
                Node node = (Node) obj;
                Element parent = node.getParent();
                if (!(parent == null || parent == this)) {
                    node = (Node) node.clone();
                }
                createContentList.add(node);
                childAdded(node);
            } else if (obj != null) {
                Text createText = getDocumentFactory().createText(obj.toString());
                createContentList.add(createText);
                childAdded(createText);
            }
        }
        this.content = createContentList;
    }

    public void clearContent() {
        if (this.content != null) {
            contentRemoved();
            this.content = null;
        }
    }

    public Node node(int i) {
        Object obj;
        if (i < 0) {
            return null;
        }
        Object obj2 = this.content;
        if (obj2 instanceof List) {
            List list = (List) obj2;
            if (i >= list.size()) {
                return null;
            }
            obj = list.get(i);
        } else {
            if (i != 0) {
                obj2 = null;
            }
            obj = obj2;
        }
        if (obj == null) {
            return null;
        }
        if (obj instanceof Node) {
            return (Node) obj;
        }
        return new DefaultText(obj.toString());
    }

    public int indexOf(Node node) {
        Object obj = this.content;
        if (obj instanceof List) {
            return ((List) obj).indexOf(node);
        }
        return (obj == null || !obj.equals(node)) ? -1 : 0;
    }

    public int nodeCount() {
        Object obj = this.content;
        if (obj instanceof List) {
            return ((List) obj).size();
        }
        return obj != null ? 1 : 0;
    }

    public Iterator nodeIterator() {
        Object obj = this.content;
        if (obj instanceof List) {
            return ((List) obj).iterator();
        }
        if (obj != null) {
            return createSingleIterator(obj);
        }
        return EMPTY_ITERATOR;
    }

    public List attributes() {
        return new ContentListFacade(this, attributeList());
    }

    public void setAttributes(List list) {
        if (list instanceof ContentListFacade) {
            list = ((ContentListFacade) list).getBackingList();
        }
        this.attributes = list;
    }

    public Iterator attributeIterator() {
        Object obj = this.attributes;
        if (obj instanceof List) {
            return ((List) obj).iterator();
        }
        if (obj != null) {
            return createSingleIterator(obj);
        }
        return EMPTY_ITERATOR;
    }

    public Attribute attribute(int i) {
        Object obj = this.attributes;
        if (obj instanceof List) {
            return (Attribute) ((List) obj).get(i);
        }
        if (obj == null || i != 0) {
            return null;
        }
        return (Attribute) obj;
    }

    public int attributeCount() {
        Object obj = this.attributes;
        if (obj instanceof List) {
            return ((List) obj).size();
        }
        return obj != null ? 1 : 0;
    }

    public Attribute attribute(String str) {
        Object obj = this.attributes;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Attribute attribute = (Attribute) list.get(i);
                if (str.equals(attribute.getName())) {
                    return attribute;
                }
            }
            return null;
        } else if (obj == null) {
            return null;
        } else {
            Attribute attribute2 = (Attribute) obj;
            if (str.equals(attribute2.getName())) {
                return attribute2;
            }
            return null;
        }
    }

    public Attribute attribute(QName qName) {
        Object obj = this.attributes;
        if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Attribute attribute = (Attribute) list.get(i);
                if (qName.equals(attribute.getQName())) {
                    return attribute;
                }
            }
            return null;
        } else if (obj == null) {
            return null;
        } else {
            Attribute attribute2 = (Attribute) obj;
            if (qName.equals(attribute2.getQName())) {
                return attribute2;
            }
            return null;
        }
    }

    public Attribute attribute(String str, Namespace namespace) {
        return attribute(getDocumentFactory().createQName(str, namespace));
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
            if (this.attributes == null) {
                this.attributes = attribute;
            } else {
                attributeList().add(attribute);
            }
            childAdded(attribute);
        }
    }

    public boolean remove(Attribute attribute) {
        Attribute attribute2;
        Object obj = this.attributes;
        boolean z = true;
        if (obj instanceof List) {
            List list = (List) obj;
            boolean remove = list.remove(attribute);
            if (remove || (attribute2 = attribute(attribute.getQName())) == null) {
                z = remove;
            } else {
                list.remove(attribute2);
            }
        } else {
            if (obj != null) {
                if (attribute.equals(obj)) {
                    this.attributes = null;
                } else if (attribute.getQName().equals(((Attribute) obj).getQName())) {
                    this.attributes = null;
                }
            }
            z = false;
        }
        if (z) {
            childRemoved(attribute);
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void addNewNode(Node node) {
        Object obj = this.content;
        if (obj == null) {
            this.content = node;
        } else if (obj instanceof List) {
            ((List) obj).add(node);
        } else {
            List createContentList = createContentList();
            createContentList.add(obj);
            createContentList.add(node);
            this.content = createContentList;
        }
        childAdded(node);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean removeNode(org.dom4j.Node r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.content
            if (r0 == 0) goto L_0x0016
            if (r0 != r3) goto L_0x000b
            r0 = 0
            r2.content = r0
            r0 = 1
            goto L_0x0017
        L_0x000b:
            boolean r1 = r0 instanceof java.util.List
            if (r1 == 0) goto L_0x0016
            java.util.List r0 = (java.util.List) r0
            boolean r0 = r0.remove(r3)
            goto L_0x0017
        L_0x0016:
            r0 = 0
        L_0x0017:
            if (r0 == 0) goto L_0x001c
            r2.childRemoved(r3)
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.tree.DefaultElement.removeNode(org.dom4j.Node):boolean");
    }

    /* access modifiers changed from: protected */
    public List contentList() {
        Object obj = this.content;
        if (obj instanceof List) {
            return (List) obj;
        }
        List createContentList = createContentList();
        if (obj != null) {
            createContentList.add(obj);
        }
        this.content = createContentList;
        return createContentList;
    }

    /* access modifiers changed from: protected */
    public List attributeList() {
        Object obj = this.attributes;
        if (obj instanceof List) {
            return (List) obj;
        }
        if (obj != null) {
            List createAttributeList = createAttributeList();
            createAttributeList.add(obj);
            this.attributes = createAttributeList;
            return createAttributeList;
        }
        List createAttributeList2 = createAttributeList();
        this.attributes = createAttributeList2;
        return createAttributeList2;
    }

    /* access modifiers changed from: protected */
    public List attributeList(int i) {
        Object obj = this.attributes;
        if (obj instanceof List) {
            return (List) obj;
        }
        if (obj != null) {
            List createAttributeList = createAttributeList(i);
            createAttributeList.add(obj);
            this.attributes = createAttributeList;
            return createAttributeList;
        }
        List createAttributeList2 = createAttributeList(i);
        this.attributes = createAttributeList2;
        return createAttributeList2;
    }

    /* access modifiers changed from: protected */
    public void setAttributeList(List list) {
        this.attributes = list;
    }

    /* access modifiers changed from: protected */
    public DocumentFactory getDocumentFactory() {
        DocumentFactory documentFactory = this.qname.getDocumentFactory();
        return documentFactory != null ? documentFactory : DOCUMENT_FACTORY;
    }
}
