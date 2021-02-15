package org.dom4j;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import junit.textui.TestRunner;
import org.dom4j.io.DOMReader;
import org.dom4j.io.DOMWriter;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXContentHandler;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.dom4j.io.XMLWriter;

public class RoundTripTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected String[] testDocuments = {"/xml/test/encode.xml", "/xml/fibo.xml", "/xml/test/schema/personal-prefix.xsd", "/xml/test/soap2.xml", "/xml/test/test_schema.xml"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.RoundTripTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testTextRoundTrip() throws Exception {
        for (String document : this.testDocuments) {
            roundTripText(getDocument(document));
        }
    }

    public void testSAXRoundTrip() throws Exception {
        for (String document : this.testDocuments) {
            roundTripSAX(getDocument(document));
        }
    }

    public void testDOMRoundTrip() throws Exception {
        for (String document : this.testDocuments) {
            roundTripDOM(getDocument(document));
        }
    }

    public void testJAXPRoundTrip() throws Exception {
        for (String document : this.testDocuments) {
            roundTripJAXP(getDocument(document));
        }
    }

    public void testFullRoundTrip() throws Exception {
        for (String document : this.testDocuments) {
            roundTripFull(getDocument(document));
        }
    }

    public void testRoundTrip() throws Exception {
        Document document = getDocument("/xml/xmlspec.xml");
        assertDocumentsEqual(document, roundTripDOM(roundTripText(roundTripSAX(roundTripDOM(roundTripSAX(document))))));
    }

    /* access modifiers changed from: protected */
    public Document roundTripDOM(Document document) throws Exception {
        Document read = new DOMReader().read(new DOMWriter().write(document));
        read.setName(document.getName());
        assertDocumentsEqual(document, read);
        return read;
    }

    /* access modifiers changed from: protected */
    public Document roundTripJAXP(Document document) throws Exception {
        Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
        StringWriter stringWriter = new StringWriter();
        newTransformer.transform(new DocumentSource(document), new StreamResult(stringWriter));
        DocumentResult documentResult = new DocumentResult();
        newTransformer.transform(new StreamSource(new StringReader(stringWriter.toString())), documentResult);
        Document document2 = documentResult.getDocument();
        document2.setName(document.getName());
        assertDocumentsEqual(document, document2);
        return document2;
    }

    /* access modifiers changed from: protected */
    public Document roundTripSAX(Document document) throws Exception {
        SAXContentHandler sAXContentHandler = new SAXContentHandler();
        new SAXWriter(sAXContentHandler, sAXContentHandler, sAXContentHandler).write(document);
        Document document2 = sAXContentHandler.getDocument();
        document2.setName(document.getName());
        assertDocumentsEqual(document, document2);
        return document2;
    }

    /* access modifiers changed from: protected */
    public Document roundTripText(Document document) throws Exception {
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter).write(document);
        Document read = new SAXReader().read((Reader) new StringReader(stringWriter.toString()));
        read.setName(document.getName());
        assertDocumentsEqual(document, read);
        return read;
    }

    /* access modifiers changed from: protected */
    public Document roundTripFull(Document document) throws Exception {
        Document roundTripText = roundTripText(roundTripSAX(roundTripDOM(document)));
        assertDocumentsEqual(document, roundTripText);
        return roundTripText;
    }
}
