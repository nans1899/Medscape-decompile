package org.dom4j.io;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;

public class StaxTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.StaxTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testEncoding() {
        try {
            XMLInputFactory.newInstance();
            try {
                Document readDocument = new STAXEventReader().readDocument((Reader) new FileReader(getFile("/xml/russArticle.xml")));
                assertEquals("russArticle.xml encoding wasn't correct", "koi8-r", readDocument.getXMLEncoding());
                StringWriter stringWriter = new StringWriter();
                new STAXEventWriter((Writer) stringWriter).writeDocument(readDocument);
                String stringWriter2 = stringWriter.toString();
                assertEquals("Unexpected xml declaration", "<?xml version='1.0' encoding='koi8-r'?>", stringWriter2.substring(0, stringWriter2.indexOf("?>") + 2));
                System.out.println(stringWriter2);
            } catch (Exception e) {
                e.printStackTrace();
                fail(e.getMessage());
            }
        } catch (FactoryConfigurationError unused) {
        }
    }
}
