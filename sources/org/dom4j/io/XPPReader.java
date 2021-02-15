package org.dom4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.xpp.ProxyXmlStartTag;
import org.gjt.xpp.XmlEndTag;
import org.gjt.xpp.XmlPullParser;
import org.gjt.xpp.XmlPullParserException;
import org.gjt.xpp.XmlPullParserFactory;

public class XPPReader {
    private DispatchHandler dispatchHandler;
    private DocumentFactory factory;
    private XmlPullParserFactory xppFactory;
    private XmlPullParser xppParser;

    public XPPReader() {
    }

    public XPPReader(DocumentFactory documentFactory) {
        this.factory = documentFactory;
    }

    public Document read(File file) throws DocumentException, IOException, XmlPullParserException {
        return read((Reader) new BufferedReader(new FileReader(file)), file.getAbsolutePath());
    }

    public Document read(URL url) throws DocumentException, IOException, XmlPullParserException {
        return read(createReader(url.openStream()), url.toExternalForm());
    }

    public Document read(String str) throws DocumentException, IOException, XmlPullParserException {
        if (str.indexOf(58) >= 0) {
            return read(new URL(str));
        }
        return read(new File(str));
    }

    public Document read(InputStream inputStream) throws DocumentException, IOException, XmlPullParserException {
        return read(createReader(inputStream));
    }

    public Document read(Reader reader) throws DocumentException, IOException, XmlPullParserException {
        getXPPParser().setInput(reader);
        return parseDocument();
    }

    public Document read(char[] cArr) throws DocumentException, IOException, XmlPullParserException {
        getXPPParser().setInput(cArr);
        return parseDocument();
    }

    public Document read(InputStream inputStream, String str) throws DocumentException, IOException, XmlPullParserException {
        return read(createReader(inputStream), str);
    }

    public Document read(Reader reader, String str) throws DocumentException, IOException, XmlPullParserException {
        Document read = read(reader);
        read.setName(str);
        return read;
    }

    public XmlPullParser getXPPParser() throws XmlPullParserException {
        if (this.xppParser == null) {
            this.xppParser = getXPPFactory().newPullParser();
        }
        return this.xppParser;
    }

    public XmlPullParserFactory getXPPFactory() throws XmlPullParserException {
        if (this.xppFactory == null) {
            this.xppFactory = XmlPullParserFactory.newInstance();
        }
        return this.xppFactory;
    }

    public void setXPPFactory(XmlPullParserFactory xmlPullParserFactory) {
        this.xppFactory = xmlPullParserFactory;
    }

    public DocumentFactory getDocumentFactory() {
        if (this.factory == null) {
            this.factory = DocumentFactory.getInstance();
        }
        return this.factory;
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.factory = documentFactory;
    }

    public void addHandler(String str, ElementHandler elementHandler) {
        getDispatchHandler().addHandler(str, elementHandler);
    }

    public void removeHandler(String str) {
        getDispatchHandler().removeHandler(str);
    }

    public void setDefaultHandler(ElementHandler elementHandler) {
        getDispatchHandler().setDefaultHandler(elementHandler);
    }

    /* access modifiers changed from: protected */
    public Document parseDocument() throws DocumentException, IOException, XmlPullParserException {
        Document createDocument = getDocumentFactory().createDocument();
        XmlPullParser xPPParser = getXPPParser();
        xPPParser.setNamespaceAware(true);
        ProxyXmlStartTag proxyXmlStartTag = new ProxyXmlStartTag();
        XmlEndTag newEndTag = this.xppFactory.newEndTag();
        Element element = null;
        while (true) {
            byte next = xPPParser.next();
            if (next == 1) {
                return createDocument;
            }
            if (next == 2) {
                xPPParser.readStartTag(proxyXmlStartTag);
                Element element2 = proxyXmlStartTag.getElement();
                if (element != null) {
                    element.add(element2);
                } else {
                    createDocument.add(element2);
                }
                element = element2;
            } else if (next == 3) {
                xPPParser.readEndTag(newEndTag);
                if (element != null) {
                    element = element.getParent();
                }
            } else if (next == 4) {
                String readContent = xPPParser.readContent();
                if (element != null) {
                    element.addText(readContent);
                } else {
                    throw new DocumentException("Cannot have text content outside of the root document");
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer("Error: unknown type: ");
                stringBuffer.append(next);
                throw new DocumentException(stringBuffer.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public DispatchHandler getDispatchHandler() {
        if (this.dispatchHandler == null) {
            this.dispatchHandler = new DispatchHandler();
        }
        return this.dispatchHandler;
    }

    /* access modifiers changed from: protected */
    public void setDispatchHandler(DispatchHandler dispatchHandler2) {
        this.dispatchHandler = dispatchHandler2;
    }

    /* access modifiers changed from: protected */
    public Reader createReader(InputStream inputStream) throws IOException {
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
