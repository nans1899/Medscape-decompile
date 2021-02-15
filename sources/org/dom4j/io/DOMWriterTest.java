package org.dom4j.io;

import java.io.StringWriter;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.w3c.dom.Node;

public class DOMWriterTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.DOMWriterTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testNamespaceBug() throws Exception {
        Document document = getDocument("/xml/namespaces.xml");
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.dom.DOMDocument");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        org.w3c.dom.Document write = new DOMWriter(cls).write(document);
        assertEquals(4, write.getDocumentElement().getAttributes().getLength());
        XMLWriter xMLWriter = new XMLWriter();
        xMLWriter.setOutputStream(System.out);
        xMLWriter.write((Document) write);
    }

    public void testBug905745() throws Exception {
        Node namedItem = new DOMWriter().write(getDocument("/xml/namespaces.xml")).getDocumentElement().getAttributes().getNamedItem("version");
        assertNotNull(namedItem);
        assertNotNull(namedItem.getLocalName());
        assertEquals("version", namedItem.getLocalName());
        assertEquals("version", namedItem.getNodeName());
    }

    public void testBug926752() throws Exception {
        Document document = getDocument("/xml/test/defaultNamespace.xml");
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.dom.DOMDocument");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        org.w3c.dom.Document write = new DOMWriter(cls).write(document);
        assertEquals(1, write.getDocumentElement().getAttributes().getLength());
        OutputFormat createCompactFormat = OutputFormat.createCompactFormat();
        createCompactFormat.setSuppressDeclaration(true);
        XMLWriter xMLWriter = new XMLWriter(createCompactFormat);
        StringWriter stringWriter = new StringWriter();
        xMLWriter.setWriter(stringWriter);
        xMLWriter.write((Document) write);
        assertEquals("<a xmlns=\"dummyNamespace\"><b><c>Hello</c></b></a>", stringWriter.toString());
    }
}
