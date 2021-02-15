package org.dom4j.io;

import javax.xml.transform.sax.SAXResult;
import org.dom4j.Document;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public class DocumentResult extends SAXResult {
    private SAXContentHandler contentHandler;

    public DocumentResult() {
        this(new SAXContentHandler());
    }

    public DocumentResult(SAXContentHandler sAXContentHandler) {
        this.contentHandler = sAXContentHandler;
        super.setHandler(sAXContentHandler);
        super.setLexicalHandler(this.contentHandler);
    }

    public Document getDocument() {
        return this.contentHandler.getDocument();
    }

    public void setHandler(ContentHandler contentHandler2) {
        if (contentHandler2 instanceof SAXContentHandler) {
            SAXContentHandler sAXContentHandler = (SAXContentHandler) contentHandler2;
            this.contentHandler = sAXContentHandler;
            super.setHandler(sAXContentHandler);
        }
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler) {
        if (lexicalHandler instanceof SAXContentHandler) {
            SAXContentHandler sAXContentHandler = (SAXContentHandler) lexicalHandler;
            this.contentHandler = sAXContentHandler;
            super.setLexicalHandler(sAXContentHandler);
        }
    }
}
