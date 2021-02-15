package org.dom4j;

import java.util.List;
import junit.textui.TestRunner;

public class ContentTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected DocumentFactory factory = new DocumentFactory();

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.ContentTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testRoot() throws Exception {
        Element rootElement = this.document.getRootElement();
        assertTrue("Has root element", rootElement != null);
        List elements = rootElement.elements("author");
        assertTrue("Root has children", elements != null && elements.size() == 2);
        Element element = (Element) elements.get(0);
        Element element2 = (Element) elements.get(1);
        assertTrue("Author1 is James", element.attributeValue("name").equals("James"));
        assertTrue("Author2 is Bob", element2.attributeValue("name").equals("Bob"));
        testGetAttributes(element);
        testGetAttributes(element2);
    }

    public void testContent() throws Exception {
        Element rootElement = this.document.getRootElement();
        boolean z = false;
        assertTrue("Has root element", rootElement != null);
        List<Object> content = rootElement.content();
        assertTrue("Root has content", content != null && content.size() >= 2);
        for (Object obj : content) {
            assertTrue("Content object is a node", obj instanceof Node);
            z = true;
        }
        assertTrue("Iteration completed", z);
    }

    public void testGetNode() throws Exception {
        Element rootElement = this.document.getRootElement();
        assertTrue("Has root element", rootElement != null);
        int nodeCount = rootElement.nodeCount();
        assertTrue("Root has correct node count", nodeCount == 2);
        int i = 0;
        boolean z = false;
        while (i < nodeCount) {
            assertTrue("Valid node returned from node()", rootElement.node(i) != null);
            i++;
            z = true;
        }
        assertTrue("Iteration completed", z);
    }

    public void testGetXPathNode() throws Exception {
        Element rootElement = this.document.getRootElement();
        assertTrue("Has root element", rootElement != null);
        int nodeCount = rootElement.nodeCount();
        assertTrue("Root has correct node count", nodeCount == 2);
        int i = 0;
        boolean z = false;
        while (i < nodeCount) {
            Node xPathResult = rootElement.getXPathResult(i);
            assertTrue("Valid node returned from node()", xPathResult != null);
            assertTrue("Node supports the parent relationship", xPathResult.supportsParent());
            i++;
            z = true;
        }
        assertTrue("Iteration completed", z);
    }

    public void testOrderOfPI() throws Exception {
        Document createDocument = this.factory.createDocument();
        createDocument.addProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"...\"");
        createDocument.addElement("root");
        List content = createDocument.content();
        assertNotNull(content);
        assertEquals(2, content.size());
        Object obj = content.get(0);
        Object obj2 = content.get(1);
        assertTrue("First element is not a PI", obj instanceof ProcessingInstruction);
        assertTrue("Second element is an element", obj2 instanceof Element);
        List content2 = DocumentHelper.parseText("<?xml version=\"1.0\" ?>\n<?xml-stylesheet type=\"text/xsl\" href=\"foo\" ?>\n<root/>").content();
        assertNotNull(content2);
        assertEquals(2, content2.size());
        Object obj3 = content2.get(0);
        Object obj4 = content2.get(1);
        assertTrue("First element is not a PI", obj3 instanceof ProcessingInstruction);
        assertTrue("Second element is an element", obj4 instanceof Element);
    }

    public void testAddingInTheMiddle() throws Exception {
        Element addElement = this.factory.createDocument().addElement("html");
        Element addElement2 = addElement.addElement("header");
        Element addElement3 = addElement.addElement("footer");
        List content = addElement.content();
        Element createElement = this.factory.createElement("foo");
        boolean z = true;
        content.add(1, createElement);
        assertTrue(content.size() == 3);
        assertTrue(content.get(0) == addElement2);
        assertTrue(content.get(1) == createElement);
        if (content.get(2) != addElement3) {
            z = false;
        }
        assertTrue(z);
    }

    public void testAddAtIndex() throws Exception {
        Element addElement = this.factory.createDocument().addElement("html");
        Element addElement2 = addElement.addElement("header");
        Element addElement3 = addElement.addElement("body");
        Element createElement = this.factory.createElement("foo");
        Element createElement2 = this.factory.createElement("bar");
        List content = addElement2.content();
        content.add(0, createElement);
        content.add(0, createElement2);
        assertEquals("foo", addElement2.node(1).getName());
        assertEquals("bar", addElement2.node(0).getName());
        Element createElement3 = this.factory.createElement("foo");
        Element createElement4 = this.factory.createElement("bar");
        List content2 = addElement3.content();
        content2.add(0, createElement3);
        content2.add(1, createElement4);
        assertEquals("foo", addElement3.node(0).getName());
        assertEquals("bar", addElement3.node(1).getName());
    }

    public void testAddAtIndex2() throws Exception {
        Element addElement = this.factory.createDocument().addElement("parent");
        Element addElement2 = addElement.addElement("child");
        Element createElement = this.factory.createElement("child2");
        List elements = addElement.elements();
        assertEquals(0, elements.indexOf(addElement2));
        elements.add(1, createElement);
        List elements2 = addElement.elements();
        assertEquals(addElement2, elements2.get(0));
        assertEquals(createElement, elements2.get(1));
    }

    /* access modifiers changed from: protected */
    public void testGetAttributes(Element element) throws Exception {
        boolean z = true;
        assertTrue("Defined value doesn't return specified default value", element.attributeValue("name", "** Default Value **") != "** Default Value **");
        if (element.attributeValue("undefined-attribute-name", "** Default Value **") != "** Default Value **") {
            z = false;
        }
        assertTrue("Undefined value returns specified default value", z);
    }
}
