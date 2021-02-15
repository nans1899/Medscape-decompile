package org.dom4j;

import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.StringReader;
import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class GetXMLEncodingTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.GetXMLEncodingTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXMLEncodingFromString() throws Exception {
        SAXReader sAXReader = new SAXReader();
        assertEquals("UTF-8", sAXReader.read(new InputSource(new ByteArrayInputStream("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root/>".getBytes("UTF-8")))).getXMLEncoding());
        assertNull(sAXReader.read((Reader) new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root/>")).getXMLEncoding());
    }

    public void testXMLEncodingFromURL() throws Exception {
        assertEquals("UTF-8", getDocument("/xml/test/encode.xml").getXMLEncoding());
        assertEquals("koi8-r", getDocument("/xml/russArticle.xml").getXMLEncoding());
    }

    public void testXMLEncodingFromStringWithHelper() throws Exception {
        assertEquals("UTF-8", DocumentHelper.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root/>").getXMLEncoding());
        assertNull(DocumentHelper.parseText("<root/>").getXMLEncoding());
    }
}
