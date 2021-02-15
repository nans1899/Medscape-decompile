package org.dom4j;

import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import junit.textui.TestRunner;
import org.dom4j.io.SAXContentHandler;
import org.xml.sax.XMLReader;

public class SAXContentHandlerTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected String[] testDocuments = {"/xml/test/test_schema.xml", "/xml/test/encode.xml", "/xml/fibo.xml", "/xml/test/schema/personal-prefix.xsd", "/xml/test/soap2.xml"};
    private XMLReader xmlReader;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.SAXContentHandlerTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        newInstance.setNamespaceAware(true);
        this.xmlReader = newInstance.newSAXParser().getXMLReader();
    }

    public void testSAXContentHandler() throws Exception {
        SAXContentHandler sAXContentHandler = new SAXContentHandler();
        this.xmlReader.setContentHandler(sAXContentHandler);
        this.xmlReader.setDTDHandler(sAXContentHandler);
        this.xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", sAXContentHandler);
        int length = this.testDocuments.length;
        for (int i = 0; i < length; i++) {
            Document document = getDocument(this.testDocuments[i]);
            this.xmlReader.parse(getFile(this.testDocuments[i]).toString());
            Document document2 = sAXContentHandler.getDocument();
            document2.setName(document.getName());
            assertDocumentsEqual(document, document2);
            assertEquals(document.asXML(), document2.asXML());
        }
    }

    public void testBug926713() throws Exception {
        List content = getDocument("/xml/test/cdata.xml").getRootElement().element("bar").content();
        assertEquals(3, content.size());
        assertEquals(3, ((Node) content.get(0)).getNodeType());
        assertEquals(4, ((Node) content.get(1)).getNodeType());
        assertEquals(3, ((Node) content.get(2)).getNodeType());
    }
}
