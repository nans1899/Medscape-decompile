package org.dom4j;

import junit.textui.TestRunner;

public class MakeElementTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.MakeElementTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testMakeElement() throws Exception {
        Document createDocument = DocumentHelper.createDocument();
        Element makeElement = DocumentHelper.makeElement(createDocument, "a/b/c");
        boolean z = true;
        assertTrue("Should return a valid element", makeElement != null);
        assertTrue("Found same element again", makeElement == DocumentHelper.makeElement(createDocument, "a/b/c"));
        makeElement.addAttribute("x", "123");
        assertEquals("Found same node via XPath", makeElement, createDocument.selectSingleNode("/a/b/c[@x='123']"));
        Element parent = makeElement.getParent();
        Element makeElement2 = DocumentHelper.makeElement(parent, "c/d/e");
        assertTrue("Should return a valid element", makeElement2 != null);
        if (makeElement2 != DocumentHelper.makeElement(parent, "c/d/e")) {
            z = false;
        }
        assertTrue("Found same element again", z);
        makeElement2.addAttribute("y", "456");
        assertEquals("Found same node via XPath", makeElement2, parent.selectSingleNode("c/d/e[@y='456']"));
    }

    public void testMakeQualifiedElement() throws Exception {
        Document createDocument = DocumentHelper.createDocument();
        Element addElement = createDocument.addElement("root");
        addElement.addNamespace("", "defaultURI");
        addElement.addNamespace("foo", "fooURI");
        addElement.addNamespace("bar", "barURI");
        Element makeElement = DocumentHelper.makeElement(createDocument, "root/foo:b/bar:c");
        boolean z = true;
        assertTrue("Should return a valid element", makeElement != null);
        assertEquals("c has a valid namespace", "barURI", makeElement.getNamespaceURI());
        assertEquals("b has a valid namespace", "fooURI", makeElement.getParent().getNamespaceURI());
        StringBuffer stringBuffer = new StringBuffer("Created: ");
        stringBuffer.append(makeElement);
        log(stringBuffer.toString());
        if (makeElement != DocumentHelper.makeElement(createDocument, "root/foo:b/bar:c")) {
            z = false;
        }
        assertTrue("Found same element again", z);
    }
}
