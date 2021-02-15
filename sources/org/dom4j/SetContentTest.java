package org.dom4j;

import junit.textui.TestRunner;

public class SetContentTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.SetContentTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDocument() throws Exception {
        this.document.setName("doc1");
        Element rootElement = this.document.getRootElement();
        boolean z = true;
        assertTrue("Current root has document", rootElement.getDocument() == this.document);
        Document createDocument = DocumentHelper.createDocument();
        createDocument.setName("doc2");
        assertTrue("Doc2 has no root element", createDocument.getRootElement() == null);
        createDocument.setContent(this.document.content());
        Element rootElement2 = createDocument.getRootElement();
        assertTrue("Current root has document", rootElement.getDocument() == this.document);
        assertTrue("Doc2 has now has root element", rootElement2 != null);
        assertTrue("Doc2 has different root element", rootElement2 != rootElement);
        assertTrue("Root element now has document", rootElement2.getDocument() == createDocument);
        testParent(createDocument.getRootElement());
        testDocument(createDocument, createDocument);
        createDocument.clearContent();
        assertTrue("New Doc has no root", createDocument.getRootElement() == null);
        if (rootElement2.getDocument() != null) {
            z = false;
        }
        assertTrue("New root has no document", z);
    }

    public void testRootElement() throws Exception {
        Document createDocument = DocumentHelper.createDocument();
        createDocument.setName("doc3");
        Element addElement = createDocument.addElement("root");
        Element addElement2 = addElement.addElement("old");
        boolean z = true;
        assertTrue("Doc3 has root element", addElement != null);
        addElement.setContent(this.document.getRootElement().content());
        assertTrue("Doc3's root now has content", addElement.nodeCount() > 0);
        assertTrue("Old element has no parent now", addElement2.getParent() == null);
        if (addElement2.getDocument() != null) {
            z = false;
        }
        assertTrue("Old element has no document now", z);
        testParent(addElement);
        testDocument(addElement, createDocument);
    }

    /* access modifiers changed from: protected */
    public void testParent(Branch branch) {
        int nodeCount = branch.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            assertTrue("Child node of root has parent of root", branch.node(i).getParent() == branch);
        }
    }

    /* access modifiers changed from: protected */
    public void testDocument(Branch branch, Document document) {
        int nodeCount = branch.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            assertTrue("Node has correct document", branch.node(i).getDocument() == document);
        }
    }
}
