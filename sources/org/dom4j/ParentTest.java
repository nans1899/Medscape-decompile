package org.dom4j;

import java.util.List;
import junit.textui.TestRunner;

public class ParentTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.ParentTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDocument() throws Exception {
        testParentRelationship(this.document.getRootElement());
    }

    public void testFragment() throws Exception {
        Element createElement = new DocumentFactory().createElement("root");
        Element addElement = createElement.addElement("child");
        Element addElement2 = createElement.addElement("child");
        testXPathNode(createElement, addElement);
        testXPathNode(createElement, addElement2);
    }

    /* access modifiers changed from: protected */
    public void testParentRelationship(Element element, List list) {
        for (Object next : list) {
            if (next instanceof Element) {
                testParentRelationship((Element) next);
            }
            testXPathNode(element, (Node) next);
        }
    }

    /* access modifiers changed from: protected */
    public void testParentRelationship(Element element) {
        testParentRelationship(element, element.attributes());
        testParentRelationship(element, element.content());
    }

    /* access modifiers changed from: protected */
    public void testXPathNode(Element element, Node node) {
        boolean z = true;
        if (node.supportsParent()) {
            StringBuffer stringBuffer = new StringBuffer("Node: ");
            stringBuffer.append(node);
            log(stringBuffer.toString());
            StringBuffer stringBuffer2 = new StringBuffer("Parent: ");
            stringBuffer2.append(element);
            log(stringBuffer2.toString());
            StringBuffer stringBuffer3 = new StringBuffer("getParent(): ");
            stringBuffer3.append(node.getParent());
            log(stringBuffer3.toString());
            StringBuffer stringBuffer4 = new StringBuffer("getParent() returns parent for: ");
            stringBuffer4.append(node);
            String stringBuffer5 = stringBuffer4.toString();
            if (node.getParent() != element) {
                z = false;
            }
            assertTrue(stringBuffer5, z);
            return;
        }
        Node asXPathResult = node.asXPathResult(element);
        StringBuffer stringBuffer6 = new StringBuffer("XPath Node supports parent for: ");
        stringBuffer6.append(asXPathResult);
        assertTrue(stringBuffer6.toString(), asXPathResult.supportsParent());
        StringBuffer stringBuffer7 = new StringBuffer("getParent() returns parent for: ");
        stringBuffer7.append(asXPathResult);
        String stringBuffer8 = stringBuffer7.toString();
        if (asXPathResult.getParent() != element) {
            z = false;
        }
        assertTrue(stringBuffer8, z);
    }
}
