package org.dom4j;

import java.util.Iterator;
import junit.textui.TestRunner;

public class NodeTypeNameTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.NodeTypeNameTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDocument() throws Exception {
        testDocument(getDocument());
    }

    public void testCDATA() throws Exception {
        testDocument("/xml/cdata.xml");
    }

    public void testNamespaces() throws Exception {
        testDocument("/xml/namespaces.xml");
        testDocument("/xml/testNamespaces.xml");
    }

    public void testPI() throws Exception {
        testDocument("/xml/testPI.xml");
    }

    public void testInline() throws Exception {
        testDocument("/xml/inline.xml");
    }

    /* access modifiers changed from: protected */
    public void testDocument(String str) throws Exception {
        testDocument(getDocument(str));
    }

    /* access modifiers changed from: protected */
    public void testDocument(Document document) throws Exception {
        assertEquals(document.getNodeTypeName(), "Document");
        DocumentType docType = document.getDocType();
        if (docType != null) {
            assertEquals(docType.getNodeTypeName(), "DocumentType");
        }
        testElement(document.getRootElement());
    }

    /* access modifiers changed from: protected */
    public void testElement(Element element) {
        assertEquals(element.getNodeTypeName(), "Element");
        Iterator attributeIterator = element.attributeIterator();
        while (attributeIterator.hasNext()) {
            assertEquals(((Attribute) attributeIterator.next()).getNodeTypeName(), "Attribute");
        }
        Iterator nodeIterator = element.nodeIterator();
        while (nodeIterator.hasNext()) {
            Node node = (Node) nodeIterator.next();
            String nodeTypeName = node.getNodeTypeName();
            if (node instanceof Attribute) {
                assertEquals(nodeTypeName, "Attribute");
            } else if (node instanceof CDATA) {
                assertEquals(nodeTypeName, "CDATA");
            } else if (node instanceof Comment) {
                assertEquals(nodeTypeName, "Comment");
            } else {
                boolean z = node instanceof Element;
                if (z) {
                    assertEquals(nodeTypeName, "Element");
                    testElement((Element) node);
                } else if (node instanceof Entity) {
                    assertEquals(nodeTypeName, "Entity");
                } else if (z) {
                    assertEquals(nodeTypeName, "Element");
                } else if (node instanceof Namespace) {
                    assertEquals(nodeTypeName, "Namespace");
                } else if (node instanceof ProcessingInstruction) {
                    assertEquals(nodeTypeName, "ProcessingInstruction");
                } else if (node instanceof Text) {
                    assertEquals(nodeTypeName, "Text");
                } else {
                    StringBuffer stringBuffer = new StringBuffer("Invalid node type: ");
                    stringBuffer.append(nodeTypeName);
                    stringBuffer.append(" for node: ");
                    stringBuffer.append(node);
                    assertTrue(stringBuffer.toString(), false);
                }
            }
        }
    }
}
