package org.dom4j;

import junit.textui.TestRunner;

public class DetachTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.DetachTest");
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
        boolean z = true;
        assertTrue("Has root element", rootElement != null);
        assertTrue("Root has no parent", rootElement.getParent() == null);
        rootElement.detach();
        assertTrue("Detached root now has no document", rootElement.getDocument() == null);
        assertTrue("Original doc now has no root element", this.document.getRootElement() == null);
        Document createDocument = DocumentHelper.createDocument();
        createDocument.setName("doc2");
        assertTrue("Doc2 has no root element", createDocument.getRootElement() == null);
        createDocument.setRootElement(rootElement);
        assertTrue("Doc2 has now has root element", createDocument.getRootElement() == rootElement);
        assertTrue("Root element now has document", rootElement.getDocument() == createDocument);
        Document createDocument2 = DocumentHelper.createDocument();
        createDocument2.setName("doc3");
        createDocument2.addElement("foo");
        assertTrue("Doc3 has root element", createDocument2.getRootElement() != null);
        Element rootElement2 = createDocument.getRootElement();
        rootElement2.detach();
        createDocument2.setRootElement(rootElement2);
        assertTrue("Doc3 now has root element", createDocument2.getRootElement() == rootElement2);
        assertSame("Root element now has a document", rootElement2.getDocument(), createDocument2);
        if (createDocument.getRootElement() != null) {
            z = false;
        }
        assertTrue("Doc2 has no root element", z);
    }
}
