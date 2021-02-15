package org.dom4j.io;

import javax.xml.transform.sax.SAXSource;
import org.dom4j.Document;
import org.dom4j.Node;
import org.xml.sax.InputSource;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

public class DocumentSource extends SAXSource {
    public static final String DOM4J_FEATURE = "http://org.dom4j.io.DoucmentSource/feature";
    private XMLReader xmlReader = new SAXWriter();

    public DocumentSource(Node node) {
        setDocument(node.getDocument());
    }

    public DocumentSource(Document document) {
        setDocument(document);
    }

    public Document getDocument() {
        return ((DocumentInputSource) getInputSource()).getDocument();
    }

    public void setDocument(Document document) {
        super.setInputSource(new DocumentInputSource(document));
    }

    public XMLReader getXMLReader() {
        return this.xmlReader;
    }

    public void setInputSource(InputSource inputSource) throws UnsupportedOperationException {
        if (inputSource instanceof DocumentInputSource) {
            super.setInputSource((DocumentInputSource) inputSource);
            return;
        }
        throw new UnsupportedOperationException();
    }

    public void setXMLReader(XMLReader xMLReader) throws UnsupportedOperationException {
        if (xMLReader instanceof SAXWriter) {
            this.xmlReader = (SAXWriter) xMLReader;
        } else if (xMLReader instanceof XMLFilter) {
            while (true) {
                XMLFilter xMLFilter = (XMLFilter) xMLReader;
                XMLReader parent = xMLFilter.getParent();
                if (parent instanceof XMLFilter) {
                    xMLReader = parent;
                } else {
                    xMLFilter.setParent(this.xmlReader);
                    this.xmlReader = xMLFilter;
                    return;
                }
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
