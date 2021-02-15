package org.dom4j.io;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXModifier {
    private HashMap modifiers = new HashMap();
    private SAXModifyReader modifyReader;
    private boolean pruneElements;
    private XMLReader xmlReader;
    private XMLWriter xmlWriter;

    public SAXModifier() {
    }

    public SAXModifier(boolean z) {
        this.pruneElements = z;
    }

    public SAXModifier(XMLReader xMLReader) {
        this.xmlReader = xMLReader;
    }

    public SAXModifier(XMLReader xMLReader, boolean z) {
        this.xmlReader = xMLReader;
    }

    public Document modify(File file) throws DocumentException {
        try {
            return installModifyReader().read(file);
        } catch (SAXModifyException e) {
            Throwable cause = e.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(InputSource inputSource) throws DocumentException {
        try {
            return installModifyReader().read(inputSource);
        } catch (SAXModifyException e) {
            Throwable cause = e.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(InputStream inputStream) throws DocumentException {
        try {
            return installModifyReader().read(inputStream);
        } catch (SAXModifyException e) {
            Throwable cause = e.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(InputStream inputStream, String str) throws DocumentException {
        try {
            return installModifyReader().read(inputStream);
        } catch (SAXModifyException e) {
            Throwable cause = e.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(Reader reader) throws DocumentException {
        try {
            return installModifyReader().read(reader);
        } catch (SAXModifyException e) {
            Throwable cause = e.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(Reader reader, String str) throws DocumentException {
        try {
            return installModifyReader().read(reader);
        } catch (SAXModifyException e) {
            Throwable cause = e.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(URL url) throws DocumentException {
        try {
            return installModifyReader().read(url);
        } catch (SAXModifyException e) {
            Throwable cause = e.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public Document modify(String str) throws DocumentException {
        try {
            return installModifyReader().read(str);
        } catch (SAXModifyException e) {
            Throwable cause = e.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    public void addModifier(String str, ElementModifier elementModifier) {
        this.modifiers.put(str, elementModifier);
    }

    public void resetModifiers() {
        this.modifiers.clear();
        getSAXModifyReader().resetHandlers();
    }

    public void removeModifier(String str) {
        this.modifiers.remove(str);
        getSAXModifyReader().removeHandler(str);
    }

    public DocumentFactory getDocumentFactory() {
        return getSAXModifyReader().getDocumentFactory();
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        getSAXModifyReader().setDocumentFactory(documentFactory);
    }

    public XMLWriter getXMLWriter() {
        return this.xmlWriter;
    }

    public void setXMLWriter(XMLWriter xMLWriter) {
        this.xmlWriter = xMLWriter;
    }

    public boolean isPruneElements() {
        return this.pruneElements;
    }

    private SAXReader installModifyReader() throws DocumentException {
        try {
            SAXModifyReader sAXModifyReader = getSAXModifyReader();
            if (isPruneElements()) {
                this.modifyReader.setDispatchHandler(new PruningDispatchHandler());
            }
            sAXModifyReader.resetHandlers();
            for (Map.Entry entry : this.modifiers.entrySet()) {
                sAXModifyReader.addHandler((String) entry.getKey(), new SAXModifyElementHandler((ElementModifier) entry.getValue()));
            }
            sAXModifyReader.setXMLWriter(getXMLWriter());
            sAXModifyReader.setXMLReader(getXMLReader());
            return sAXModifyReader;
        } catch (SAXException e) {
            throw new DocumentException(e.getMessage(), e);
        }
    }

    private XMLReader getXMLReader() throws SAXException {
        if (this.xmlReader == null) {
            this.xmlReader = SAXHelper.createXMLReader(false);
        }
        return this.xmlReader;
    }

    private SAXModifyReader getSAXModifyReader() {
        if (this.modifyReader == null) {
            this.modifyReader = new SAXModifyReader();
        }
        return this.modifyReader;
    }
}
