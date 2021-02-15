package org.xmlpull.v1.builder.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.builder.XmlBuilderException;
import org.xmlpull.v1.builder.XmlComment;
import org.xmlpull.v1.builder.XmlContainer;
import org.xmlpull.v1.builder.XmlDoctype;
import org.xmlpull.v1.builder.XmlDocument;
import org.xmlpull.v1.builder.XmlElement;
import org.xmlpull.v1.builder.XmlNamespace;
import org.xmlpull.v1.builder.XmlNotation;
import org.xmlpull.v1.builder.XmlProcessingInstruction;

public class XmlDocumentImpl implements XmlDocument {
    private String characterEncoding;
    private List children = new ArrayList();
    private XmlElement root;
    private Boolean standalone;
    private String version;

    public XmlDocumentImpl(String str, Boolean bool, String str2) {
        this.version = str;
        this.standalone = bool;
        this.characterEncoding = str2;
    }

    public String getVersion() {
        return this.version;
    }

    public Boolean isStandalone() {
        return this.standalone;
    }

    public String getCharacterEncodingScheme() {
        return this.characterEncoding;
    }

    public void setCharacterEncodingScheme(String str) {
        this.characterEncoding = str;
    }

    public XmlProcessingInstruction newProcessingInstruction(String str, String str2) {
        throw new XmlBuilderException("not implemented");
    }

    public XmlProcessingInstruction addProcessingInstruction(String str, String str2) {
        throw new XmlBuilderException("not implemented");
    }

    public Iterator children() {
        return this.children.iterator();
    }

    public void remocveAllUnparsedEntities() {
        throw new XmlBuilderException("not implemented");
    }

    public void setDocumentElement(XmlElement xmlElement) {
        boolean z = false;
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i) == this.root) {
                this.children.set(i, xmlElement);
                z = true;
            }
        }
        if (!z) {
            this.children.add(xmlElement);
        }
        this.root = xmlElement;
        xmlElement.setParent(this);
    }

    public void insertChild(int i, Object obj) {
        throw new XmlBuilderException("not implemented");
    }

    public XmlComment addComment(String str) {
        XmlCommentImpl xmlCommentImpl = new XmlCommentImpl(this, str);
        this.children.add(xmlCommentImpl);
        return xmlCommentImpl;
    }

    public XmlDoctype newDoctype(String str, String str2) {
        throw new XmlBuilderException("not implemented");
    }

    public Iterator unparsedEntities() {
        throw new XmlBuilderException("not implemented");
    }

    public void removeAllChildren() {
        throw new XmlBuilderException("not implemented");
    }

    public XmlComment newComment(String str) {
        return new XmlCommentImpl((XmlContainer) null, str);
    }

    public void removeAllNotations() {
        throw new XmlBuilderException("not implemented");
    }

    public XmlDoctype addDoctype(String str, String str2) {
        throw new XmlBuilderException("not implemented");
    }

    public void addChild(Object obj) {
        throw new XmlBuilderException("not implemented");
    }

    public XmlNotation addNotation(String str, String str2, String str3, String str4) {
        throw new XmlBuilderException("not implemented");
    }

    public String getBaseUri() {
        throw new XmlBuilderException("not implemented");
    }

    public Iterator notations() {
        throw new XmlBuilderException("not implemented");
    }

    public XmlElement addDocumentElement(String str) {
        return addDocumentElement((XmlNamespace) null, str);
    }

    public XmlElement addDocumentElement(XmlNamespace xmlNamespace, String str) {
        XmlElementImpl xmlElementImpl = new XmlElementImpl(xmlNamespace, str);
        if (getDocumentElement() == null) {
            setDocumentElement(xmlElementImpl);
            return xmlElementImpl;
        }
        throw new XmlBuilderException("document already has root element");
    }

    public boolean isAllDeclarationsProcessed() {
        throw new XmlBuilderException("not implemented");
    }

    public XmlElement getDocumentElement() {
        return this.root;
    }
}
