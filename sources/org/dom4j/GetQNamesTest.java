package org.dom4j;

import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;

public class GetQNamesTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.GetQNamesTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testQNames() throws Exception {
        DocumentFactory documentFactory = new DocumentFactory();
        SAXReader sAXReader = new SAXReader();
        sAXReader.setDocumentFactory(documentFactory);
        getDocument("/xml/test/soap2.xml", sAXReader);
        assertEquals("Number of QNames not correct", 15, documentFactory.getQNames().size());
    }

    public void testRename() throws Exception {
        Element addElement = DocumentHelper.createDocument().addElement("foo");
        assertEquals("named correctly", "foo", addElement.getName());
        addElement.setName("bar");
        assertEquals("named correctly", "bar", addElement.getName());
        QName qName = addElement.getQName("xyz");
        addElement.setQName(qName);
        assertEquals("QNamed correctly", qName, addElement.getQName());
    }
}
