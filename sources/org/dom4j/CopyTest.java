package org.dom4j;

import java.util.List;
import junit.textui.TestRunner;

public class CopyTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.CopyTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testRoot() throws Exception {
        this.document.setName("doc1");
        Element rootElement = this.document.getRootElement();
        List elements = rootElement.elements("author");
        assertTrue("Should be at least 2 authors", elements.size() == 2);
        testCopy(rootElement);
        testCopy((Element) elements.get(0));
        testCopy((Element) elements.get(1));
    }

    /* access modifiers changed from: protected */
    public void testCopy(Element element) throws Exception {
        assertTrue("Not null", element != null);
        int attributeCount = element.attributeCount();
        int nodeCount = element.nodeCount();
        Element createCopy = element.createCopy();
        assertEquals("Node size not equal after copy", element.nodeCount(), nodeCount);
        assertTrue("Same attribute size after copy", element.attributeCount() == attributeCount);
        assertTrue("Copy has same node size", createCopy.nodeCount() == nodeCount);
        assertTrue("Copy has same attribute size", createCopy.attributeCount() == attributeCount);
        for (int i = 0; i < attributeCount; i++) {
            Attribute attribute = element.attribute(i);
            Attribute attribute2 = createCopy.attribute(i);
            StringBuffer stringBuffer = new StringBuffer("Attribute: ");
            stringBuffer.append(i);
            stringBuffer.append(" name is equal");
            assertTrue(stringBuffer.toString(), attribute.getName().equals(attribute2.getName()));
            StringBuffer stringBuffer2 = new StringBuffer("Attribute: ");
            stringBuffer2.append(i);
            stringBuffer2.append(" value is equal");
            assertTrue(stringBuffer2.toString(), attribute.getValue().equals(attribute2.getValue()));
        }
        for (int i2 = 0; i2 < nodeCount; i2++) {
            Node node = element.node(i2);
            Node node2 = createCopy.node(i2);
            StringBuffer stringBuffer3 = new StringBuffer("Node: ");
            stringBuffer3.append(i2);
            stringBuffer3.append(" type is equal");
            assertTrue(stringBuffer3.toString(), node.getNodeType() == node2.getNodeType());
            StringBuffer stringBuffer4 = new StringBuffer("Node: ");
            stringBuffer4.append(i2);
            stringBuffer4.append(" value is equal");
            assertTrue(stringBuffer4.toString(), node.getText().equals(node2.getText()));
        }
    }
}
