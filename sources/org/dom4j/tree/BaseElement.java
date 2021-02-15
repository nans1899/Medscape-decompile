package org.dom4j.tree;

import java.util.List;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class BaseElement extends AbstractElement {
    protected List attributes;
    protected List content;
    private Branch parentBranch;
    private QName qname;

    public boolean supportsParent() {
        return true;
    }

    public BaseElement(String str) {
        this.qname = getDocumentFactory().createQName(str);
    }

    public BaseElement(QName qName) {
        this.qname = qName;
    }

    public BaseElement(String str, Namespace namespace) {
        this.qname = getDocumentFactory().createQName(str, namespace);
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

    public void clearContent() {
        contentList().clear();
    }

    public void setContent(List list) {
        this.content = list;
        if (list instanceof ContentListFacade) {
            this.content = ((ContentListFacade) list).getBackingList();
        }
    }

    public void setAttributes(List list) {
        this.attributes = list;
        if (list instanceof ContentListFacade) {
            this.attributes = ((ContentListFacade) list).getBackingList();
        }
    }

    /* access modifiers changed from: protected */
    public List contentList() {
        if (this.content == null) {
            this.content = createContentList();
        }
        return this.content;
    }

    /* access modifiers changed from: protected */
    public List attributeList() {
        if (this.attributes == null) {
            this.attributes = createAttributeList();
        }
        return this.attributes;
    }

    /* access modifiers changed from: protected */
    public List attributeList(int i) {
        if (this.attributes == null) {
            this.attributes = createAttributeList(i);
        }
        return this.attributes;
    }

    /* access modifiers changed from: protected */
    public void setAttributeList(List list) {
        this.attributes = list;
    }
}
