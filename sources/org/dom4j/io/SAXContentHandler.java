package org.dom4j.io;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.QName;
import org.dom4j.dtd.AttributeDecl;
import org.dom4j.dtd.ElementDecl;
import org.dom4j.dtd.ExternalEntityDecl;
import org.dom4j.dtd.InternalEntityDecl;
import org.dom4j.tree.AbstractElement;
import org.dom4j.tree.NamespaceStack;
import org.xml.sax.Attributes;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public class SAXContentHandler extends DefaultHandler implements LexicalHandler, DeclHandler, DTDHandler {
    private Map availableNamespaceMap;
    private StringBuffer cdataText;
    private Element currentElement;
    private int declaredNamespaceIndex;
    private List declaredNamespaceList;
    private Document document;
    private DocumentFactory documentFactory;
    private ElementHandler elementHandler;
    private ElementStack elementStack;
    private String entity;
    private int entityLevel;
    private EntityResolver entityResolver;
    private List externalDTDDeclarations;
    private boolean ignoreComments;
    private boolean includeExternalDTDDeclarations;
    private boolean includeInternalDTDDeclarations;
    private InputSource inputSource;
    private boolean insideCDATASection;
    private boolean insideDTDSection;
    private List internalDTDDeclarations;
    private boolean internalDTDsubset;
    private Locator locator;
    private boolean mergeAdjacentText;
    private NamespaceStack namespaceStack;
    private boolean stripWhitespaceText;
    private StringBuffer textBuffer;
    private boolean textInTextBuffer;

    public void notationDecl(String str, String str2, String str3) throws SAXException {
    }

    public void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException {
    }

    public void warning(SAXParseException sAXParseException) throws SAXException {
    }

    public SAXContentHandler() {
        this(DocumentFactory.getInstance());
    }

    public SAXContentHandler(DocumentFactory documentFactory2) {
        this(documentFactory2, (ElementHandler) null);
    }

    public SAXContentHandler(DocumentFactory documentFactory2, ElementHandler elementHandler2) {
        this(documentFactory2, elementHandler2, (ElementStack) null);
        this.elementStack = createElementStack();
    }

    public SAXContentHandler(DocumentFactory documentFactory2, ElementHandler elementHandler2, ElementStack elementStack2) {
        this.availableNamespaceMap = new HashMap();
        this.declaredNamespaceList = new ArrayList();
        this.includeInternalDTDDeclarations = false;
        this.includeExternalDTDDeclarations = false;
        this.internalDTDsubset = false;
        this.mergeAdjacentText = false;
        this.textInTextBuffer = false;
        this.ignoreComments = false;
        this.stripWhitespaceText = false;
        this.documentFactory = documentFactory2;
        this.elementHandler = elementHandler2;
        this.elementStack = elementStack2;
        this.namespaceStack = new NamespaceStack(documentFactory2);
    }

    public Document getDocument() {
        if (this.document == null) {
            this.document = createDocument();
        }
        return this.document;
    }

    public void setDocumentLocator(Locator locator2) {
        this.locator = locator2;
    }

    public void processingInstruction(String str, String str2) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            completeCurrentTextNode();
        }
        Element element = this.currentElement;
        if (element != null) {
            element.addProcessingInstruction(str, str2);
        } else {
            getDocument().addProcessingInstruction(str, str2);
        }
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
        this.namespaceStack.push(str, str2);
    }

    public void endPrefixMapping(String str) throws SAXException {
        this.namespaceStack.pop(str);
        this.declaredNamespaceIndex = this.namespaceStack.size();
    }

    public void startDocument() throws SAXException {
        this.document = null;
        this.currentElement = null;
        this.elementStack.clear();
        ElementHandler elementHandler2 = this.elementHandler;
        if (elementHandler2 != null && (elementHandler2 instanceof DispatchHandler)) {
            this.elementStack.setDispatchHandler((DispatchHandler) elementHandler2);
        }
        this.namespaceStack.clear();
        this.declaredNamespaceIndex = 0;
        if (this.mergeAdjacentText && this.textBuffer == null) {
            this.textBuffer = new StringBuffer();
        }
        this.textInTextBuffer = false;
    }

    public void endDocument() throws SAXException {
        this.namespaceStack.clear();
        this.elementStack.clear();
        this.currentElement = null;
        this.textBuffer = null;
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            completeCurrentTextNode();
        }
        QName qName = this.namespaceStack.getQName(str, str2, str3);
        Branch branch = this.currentElement;
        if (branch == null) {
            branch = getDocument();
        }
        Element addElement = branch.addElement(qName);
        addDeclaredNamespaces(addElement);
        addAttributes(addElement, attributes);
        this.elementStack.pushElement(addElement);
        this.currentElement = addElement;
        this.entity = null;
        ElementHandler elementHandler2 = this.elementHandler;
        if (elementHandler2 != null) {
            elementHandler2.onStart(this.elementStack);
        }
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            completeCurrentTextNode();
        }
        ElementHandler elementHandler2 = this.elementHandler;
        if (!(elementHandler2 == null || this.currentElement == null)) {
            elementHandler2.onEnd(this.elementStack);
        }
        this.elementStack.popElement();
        this.currentElement = this.elementStack.peekElement();
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        Element element;
        if (i2 != 0 && (element = this.currentElement) != null) {
            if (this.entity != null) {
                if (this.mergeAdjacentText && this.textInTextBuffer) {
                    completeCurrentTextNode();
                }
                this.currentElement.addEntity(this.entity, new String(cArr, i, i2));
                this.entity = null;
            } else if (this.insideCDATASection) {
                if (this.mergeAdjacentText && this.textInTextBuffer) {
                    completeCurrentTextNode();
                }
                this.cdataText.append(new String(cArr, i, i2));
            } else if (this.mergeAdjacentText) {
                this.textBuffer.append(cArr, i, i2);
                this.textInTextBuffer = true;
            } else {
                element.addText(new String(cArr, i, i2));
            }
        }
    }

    public void error(SAXParseException sAXParseException) throws SAXException {
        throw sAXParseException;
    }

    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        throw sAXParseException;
    }

    public void startDTD(String str, String str2, String str3) throws SAXException {
        getDocument().addDocType(str, str2, str3);
        this.insideDTDSection = true;
        this.internalDTDsubset = true;
    }

    public void endDTD() throws SAXException {
        this.insideDTDSection = false;
        DocumentType docType = getDocument().getDocType();
        if (docType != null) {
            List list = this.internalDTDDeclarations;
            if (list != null) {
                docType.setInternalDeclarations(list);
            }
            List list2 = this.externalDTDDeclarations;
            if (list2 != null) {
                docType.setExternalDeclarations(list2);
            }
        }
        this.internalDTDDeclarations = null;
        this.externalDTDDeclarations = null;
    }

    public void startEntity(String str) throws SAXException {
        this.entityLevel++;
        this.entity = null;
        if (!this.insideDTDSection && !isIgnorableEntity(str)) {
            this.entity = str;
        }
        this.internalDTDsubset = false;
    }

    public void endEntity(String str) throws SAXException {
        int i = this.entityLevel - 1;
        this.entityLevel = i;
        this.entity = null;
        if (i == 0) {
            this.internalDTDsubset = true;
        }
    }

    public void startCDATA() throws SAXException {
        this.insideCDATASection = true;
        this.cdataText = new StringBuffer();
    }

    public void endCDATA() throws SAXException {
        this.insideCDATASection = false;
        this.currentElement.addCDATA(this.cdataText.toString());
    }

    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (!this.ignoreComments) {
            if (this.mergeAdjacentText && this.textInTextBuffer) {
                completeCurrentTextNode();
            }
            String str = new String(cArr, i, i2);
            if (!this.insideDTDSection && str.length() > 0) {
                Element element = this.currentElement;
                if (element != null) {
                    element.addComment(str);
                } else {
                    getDocument().addComment(str);
                }
            }
        }
    }

    public void elementDecl(String str, String str2) throws SAXException {
        if (this.internalDTDsubset) {
            if (this.includeInternalDTDDeclarations) {
                addDTDDeclaration(new ElementDecl(str, str2));
            }
        } else if (this.includeExternalDTDDeclarations) {
            addExternalDTDDeclaration(new ElementDecl(str, str2));
        }
    }

    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
        if (this.internalDTDsubset) {
            if (this.includeInternalDTDDeclarations) {
                addDTDDeclaration(new AttributeDecl(str, str2, str3, str4, str5));
            }
        } else if (this.includeExternalDTDDeclarations) {
            addExternalDTDDeclaration(new AttributeDecl(str, str2, str3, str4, str5));
        }
    }

    public void internalEntityDecl(String str, String str2) throws SAXException {
        if (this.internalDTDsubset) {
            if (this.includeInternalDTDDeclarations) {
                addDTDDeclaration(new InternalEntityDecl(str, str2));
            }
        } else if (this.includeExternalDTDDeclarations) {
            addExternalDTDDeclaration(new InternalEntityDecl(str, str2));
        }
    }

    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
        ExternalEntityDecl externalEntityDecl = new ExternalEntityDecl(str, str2, str3);
        if (this.internalDTDsubset) {
            if (this.includeInternalDTDDeclarations) {
                addDTDDeclaration(externalEntityDecl);
            }
        } else if (this.includeExternalDTDDeclarations) {
            addExternalDTDDeclaration(externalEntityDecl);
        }
    }

    public ElementStack getElementStack() {
        return this.elementStack;
    }

    public void setElementStack(ElementStack elementStack2) {
        this.elementStack = elementStack2;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver2) {
        this.entityResolver = entityResolver2;
    }

    public InputSource getInputSource() {
        return this.inputSource;
    }

    public void setInputSource(InputSource inputSource2) {
        this.inputSource = inputSource2;
    }

    public boolean isIncludeInternalDTDDeclarations() {
        return this.includeInternalDTDDeclarations;
    }

    public void setIncludeInternalDTDDeclarations(boolean z) {
        this.includeInternalDTDDeclarations = z;
    }

    public boolean isIncludeExternalDTDDeclarations() {
        return this.includeExternalDTDDeclarations;
    }

    public void setIncludeExternalDTDDeclarations(boolean z) {
        this.includeExternalDTDDeclarations = z;
    }

    public boolean isMergeAdjacentText() {
        return this.mergeAdjacentText;
    }

    public void setMergeAdjacentText(boolean z) {
        this.mergeAdjacentText = z;
    }

    public boolean isStripWhitespaceText() {
        return this.stripWhitespaceText;
    }

    public void setStripWhitespaceText(boolean z) {
        this.stripWhitespaceText = z;
    }

    public boolean isIgnoreComments() {
        return this.ignoreComments;
    }

    public void setIgnoreComments(boolean z) {
        this.ignoreComments = z;
    }

    /* access modifiers changed from: protected */
    public void completeCurrentTextNode() {
        boolean z;
        if (this.stripWhitespaceText) {
            int length = this.textBuffer.length();
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = true;
                    break;
                } else if (!Character.isWhitespace(this.textBuffer.charAt(i))) {
                    z = false;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                this.currentElement.addText(this.textBuffer.toString());
            }
        } else {
            this.currentElement.addText(this.textBuffer.toString());
        }
        this.textBuffer.setLength(0);
        this.textInTextBuffer = false;
    }

    /* access modifiers changed from: protected */
    public Document createDocument() {
        Document createDocument = this.documentFactory.createDocument(getEncoding());
        createDocument.setEntityResolver(this.entityResolver);
        InputSource inputSource2 = this.inputSource;
        if (inputSource2 != null) {
            createDocument.setName(inputSource2.getSystemId());
        }
        return createDocument;
    }

    private String getEncoding() {
        Locator locator2 = this.locator;
        if (locator2 == null) {
            return null;
        }
        try {
            Method method = locator2.getClass().getMethod("getEncoding", new Class[0]);
            if (method != null) {
                return (String) method.invoke(this.locator, (Object[]) null);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isIgnorableEntity(String str) {
        return "amp".equals(str) || "apos".equals(str) || "gt".equals(str) || "lt".equals(str) || "quot".equals(str);
    }

    /* access modifiers changed from: protected */
    public void addDeclaredNamespaces(Element element) {
        element.getNamespace();
        int size = this.namespaceStack.size();
        while (true) {
            int i = this.declaredNamespaceIndex;
            if (i < size) {
                element.add(this.namespaceStack.getNamespace(i));
                this.declaredNamespaceIndex++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addAttributes(Element element, Attributes attributes) {
        if (element instanceof AbstractElement) {
            ((AbstractElement) element).setAttributes(attributes, this.namespaceStack, false);
            return;
        }
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String qName = attributes.getQName(i);
            if (!qName.startsWith(XMLConstants.XMLNS_ATTRIBUTE)) {
                String uri = attributes.getURI(i);
                String localName = attributes.getLocalName(i);
                element.addAttribute(this.namespaceStack.getAttributeQName(uri, localName, qName), attributes.getValue(i));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addDTDDeclaration(Object obj) {
        if (this.internalDTDDeclarations == null) {
            this.internalDTDDeclarations = new ArrayList();
        }
        this.internalDTDDeclarations.add(obj);
    }

    /* access modifiers changed from: protected */
    public void addExternalDTDDeclaration(Object obj) {
        if (this.externalDTDDeclarations == null) {
            this.externalDTDDeclarations = new ArrayList();
        }
        this.externalDTDDeclarations.add(obj);
    }

    /* access modifiers changed from: protected */
    public ElementStack createElementStack() {
        return new ElementStack();
    }
}
