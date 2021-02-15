package org.dom4j.io;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.xml.sax.XMLReader;

class JAXPHelper {
    protected JAXPHelper() {
    }

    public static XMLReader createXMLReader(boolean z, boolean z2) throws Exception {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        newInstance.setValidating(z);
        newInstance.setNamespaceAware(z2);
        return newInstance.newSAXParser().getXMLReader();
    }

    public static Document createDocument(boolean z, boolean z2) throws Exception {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setValidating(z);
        newInstance.setNamespaceAware(z2);
        return newInstance.newDocumentBuilder().newDocument();
    }
}
