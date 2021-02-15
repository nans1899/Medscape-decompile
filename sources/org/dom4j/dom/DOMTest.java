package org.dom4j.dom;

import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.io.DOMWriter;
import org.dom4j.io.SAXReader;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    private long attributes;
    private long characters;
    private long elements;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.dom.DOMTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testCount() throws Exception {
        DOMWriter dOMWriter = new DOMWriter();
        long currentTimeMillis = System.currentTimeMillis();
        Document write = dOMWriter.write(this.document);
        long currentTimeMillis2 = System.currentTimeMillis();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Converting to a W3C Document took: ");
        stringBuffer.append(currentTimeMillis2 - currentTimeMillis);
        stringBuffer.append(" milliseconds");
        printStream.println(stringBuffer.toString());
        traverse(write);
        StringBuffer stringBuffer2 = new StringBuffer("elements: ");
        stringBuffer2.append(this.elements);
        stringBuffer2.append(" attributes: ");
        stringBuffer2.append(this.attributes);
        stringBuffer2.append(" characters: ");
        stringBuffer2.append(this.characters);
        log(stringBuffer2.toString());
    }

    public void testNamespace() throws Exception {
        DOMDocument dOMDocument = (DOMDocument) new SAXReader(DOMDocumentFactory.getInstance()).read((Reader) new StringReader("<prefix:root xmlns:prefix=\"myuri\" />"));
        assertEquals("namespace prefix not correct", "prefix", dOMDocument.getRootElement().getNamespace().getPrefix());
        assertEquals("namespace uri not correct", "myuri", dOMDocument.getRootElement().getNamespace().getURI());
        System.out.println(dOMDocument.asXML());
    }

    public void testClassCastBug() throws Exception {
        DOMDocument dOMDocument = new DOMDocument("Root");
        Element createElement = dOMDocument.createElement("Parent");
        createElement.setAttribute("name", "N01");
        createElement.setAttribute("id", "ID01");
        dOMDocument.appendChild(createElement);
    }

    public void testReplaceChild() throws Exception {
        DOMDocument dOMDocument = new DOMDocument("Root");
        Element createElement = dOMDocument.createElement("Parent");
        Element createElement2 = dOMDocument.createElement("FirstChild");
        Element createElement3 = dOMDocument.createElement("SecondChild");
        Element createElement4 = dOMDocument.createElement("ThirdChild");
        dOMDocument.appendChild(createElement);
        createElement.appendChild(createElement2);
        createElement.appendChild(createElement3);
        createElement.appendChild(createElement4);
        Element createElement5 = dOMDocument.createElement("NewFirst");
        assertEquals((Element) createElement.replaceChild(createElement5, createElement2), createElement2);
        Node item = createElement.getChildNodes().item(0);
        assertEquals(1, item.getNodeType());
        assertEquals(createElement5, item);
        try {
            createElement.replaceChild(createElement5, dOMDocument.createElement("No Child"));
            fail("DOMException not thrown");
        } catch (DOMException e) {
            assertEquals(8, e.code);
        }
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = getDocument("/xml/contents.xml", new SAXReader(DOMDocumentFactory.getInstance()));
    }

    /* access modifiers changed from: protected */
    public void traverse(Node node) {
        NodeList childNodes;
        if (node != null) {
            short nodeType = node.getNodeType();
            int i = 0;
            if (nodeType == 1) {
                this.elements++;
                NamedNodeMap attributes2 = node.getAttributes();
                if (attributes2 != null) {
                    this.attributes += (long) attributes2.getLength();
                }
                NodeList childNodes2 = node.getChildNodes();
                if (childNodes2 != null) {
                    int length = childNodes2.getLength();
                    while (i < length) {
                        traverse(childNodes2.item(i));
                        i++;
                    }
                }
            } else if (nodeType == 9) {
                this.elements = 0;
                this.attributes = 0;
                this.characters = 0;
                traverse(((Document) node).getDocumentElement());
            } else if (nodeType == 3) {
                this.characters += (long) node.getNodeValue().length();
            } else if (nodeType == 4) {
                this.characters += (long) node.getNodeValue().length();
            } else if (nodeType == 5 && (childNodes = node.getChildNodes()) != null) {
                int length2 = childNodes.getLength();
                while (i < length2) {
                    traverse(childNodes.item(i));
                    i++;
                }
            }
        }
    }
}
