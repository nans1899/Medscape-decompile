package org.dom4j;

import junit.textui.TestRunner;

public class AddNodeTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.AddNodeTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDom4jAddNodeClone() {
        Document createDocument = DocumentHelper.createDocument();
        createDocument.addElement("document").addElement("header").addText("Some Text");
        Document createDocument2 = DocumentHelper.createDocument();
        createDocument2.addElement("document").add((Element) createDocument.selectSingleNode("/document/header").clone());
        assertEquals(createDocument2.asXML(), createDocument.asXML());
    }

    public void testDom4jAddNodeCreateCopy() {
        Document createDocument = DocumentHelper.createDocument();
        createDocument.addElement("document").addElement("header").addText("Some Text");
        Document createDocument2 = DocumentHelper.createDocument();
        createDocument2.addElement("document").add(((Element) createDocument.selectSingleNode("/document/header")).createCopy());
        assertEquals(createDocument2.asXML(), createDocument.asXML());
    }

    public void testDom4jAddNodeBug() {
        Document createDocument = DocumentHelper.createDocument();
        createDocument.addElement("document").addElement("header").addText("Some Text");
        try {
            DocumentHelper.createDocument().addElement("document").add((Element) createDocument.selectSingleNode("/document/header"));
            fail();
        } catch (IllegalAddException unused) {
        }
    }
}
