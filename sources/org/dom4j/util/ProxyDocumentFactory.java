package org.dom4j.util;

import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.NodeFilter;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.XPath;
import org.dom4j.rule.Pattern;
import org.jaxen.VariableContext;

public abstract class ProxyDocumentFactory {
    private DocumentFactory proxy;

    public ProxyDocumentFactory() {
        this.proxy = DocumentFactory.getInstance();
    }

    public ProxyDocumentFactory(DocumentFactory documentFactory) {
        this.proxy = documentFactory;
    }

    public Document createDocument() {
        return this.proxy.createDocument();
    }

    public Document createDocument(Element element) {
        return this.proxy.createDocument(element);
    }

    public DocumentType createDocType(String str, String str2, String str3) {
        return this.proxy.createDocType(str, str2, str3);
    }

    public Element createElement(QName qName) {
        return this.proxy.createElement(qName);
    }

    public Element createElement(String str) {
        return this.proxy.createElement(str);
    }

    public Attribute createAttribute(Element element, QName qName, String str) {
        return this.proxy.createAttribute(element, qName, str);
    }

    public Attribute createAttribute(Element element, String str, String str2) {
        return this.proxy.createAttribute(element, str, str2);
    }

    public CDATA createCDATA(String str) {
        return this.proxy.createCDATA(str);
    }

    public Comment createComment(String str) {
        return this.proxy.createComment(str);
    }

    public Text createText(String str) {
        return this.proxy.createText(str);
    }

    public Entity createEntity(String str, String str2) {
        return this.proxy.createEntity(str, str2);
    }

    public Namespace createNamespace(String str, String str2) {
        return this.proxy.createNamespace(str, str2);
    }

    public ProcessingInstruction createProcessingInstruction(String str, String str2) {
        return this.proxy.createProcessingInstruction(str, str2);
    }

    public ProcessingInstruction createProcessingInstruction(String str, Map map) {
        return this.proxy.createProcessingInstruction(str, map);
    }

    public QName createQName(String str, Namespace namespace) {
        return this.proxy.createQName(str, namespace);
    }

    public QName createQName(String str) {
        return this.proxy.createQName(str);
    }

    public QName createQName(String str, String str2, String str3) {
        return this.proxy.createQName(str, str2, str3);
    }

    public QName createQName(String str, String str2) {
        return this.proxy.createQName(str, str2);
    }

    public XPath createXPath(String str) {
        return this.proxy.createXPath(str);
    }

    public XPath createXPath(String str, VariableContext variableContext) {
        return this.proxy.createXPath(str, variableContext);
    }

    public NodeFilter createXPathFilter(String str, VariableContext variableContext) {
        return this.proxy.createXPathFilter(str, variableContext);
    }

    public NodeFilter createXPathFilter(String str) {
        return this.proxy.createXPathFilter(str);
    }

    public Pattern createPattern(String str) {
        return this.proxy.createPattern(str);
    }

    /* access modifiers changed from: protected */
    public DocumentFactory getProxy() {
        return this.proxy;
    }

    /* access modifiers changed from: protected */
    public void setProxy(DocumentFactory documentFactory) {
        if (documentFactory == null) {
            documentFactory = DocumentFactory.getInstance();
        }
        this.proxy = documentFactory;
    }
}
