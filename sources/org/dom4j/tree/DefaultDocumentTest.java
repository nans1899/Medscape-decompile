package org.dom4j.tree;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class DefaultDocumentTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.tree.DefaultDocumentTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDoubleRootElement() {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        createDocument.addElement("root");
        Element createElement = DocumentFactory.getInstance().createElement("anotherRoot");
        try {
            createDocument.add(createElement);
            fail();
        } catch (IllegalAddException e) {
            assertTrue(e.getMessage().indexOf(createElement.toString()) != -1);
        }
    }

    public void testBug799656() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        createDocument.addElement("root").setText("text with an ü in it");
        System.out.println(createDocument.asXML());
        DocumentHelper.parseText(createDocument.asXML());
    }

    public void testAsXMLWithEncoding() throws Exception {
        DefaultDocument defaultDocument = new DefaultDocument();
        defaultDocument.addElement("root");
        defaultDocument.setXMLEncoding("ISO-8859-1");
        Document parseText = DocumentHelper.parseText("<?xml version='1.0' encoding='ISO-8859-1'?><root/>");
        String asXML = defaultDocument.asXML();
        String asXML2 = parseText.asXML();
        boolean z = true;
        assertTrue(asXML.indexOf("ISO-8859-1") != -1);
        if (asXML2.indexOf("ISO-8859-1") == -1) {
            z = false;
        }
        assertTrue(z);
    }

    public void testBug1156909() throws Exception {
        assertEquals("XMLEncoding not correct", "ISO-8859-1", DocumentHelper.parseText("<?xml version='1.0' encoding='ISO-8859-1'?><root/>").getXMLEncoding());
    }

    public void testAsXMLWithEncodingAndContent() throws Exception {
        DefaultDocument defaultDocument = new DefaultDocument();
        defaultDocument.setXMLEncoding("UTF-16");
        defaultDocument.addElement("root").setText("text with an ü in it");
        String asXML = defaultDocument.asXML();
        boolean z = true;
        assertTrue(asXML.indexOf("UTF-16") != -1);
        if (asXML.indexOf(252) == -1) {
            z = false;
        }
        assertTrue(z);
    }

    public void testEncoding() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument("koi8-r");
        createDocument.addElement("root").setText("text with an ü in it");
        System.out.println(createDocument.asXML());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputFormat createPrettyPrint = OutputFormat.createPrettyPrint();
        createPrettyPrint.setEncoding("koi8-r");
        new XMLWriter((OutputStream) byteArrayOutputStream, createPrettyPrint).write(createDocument);
        String byteArrayOutputStream2 = byteArrayOutputStream.toString("koi8-r");
        System.out.println(byteArrayOutputStream2);
        DocumentHelper.parseText(byteArrayOutputStream2);
    }
}
