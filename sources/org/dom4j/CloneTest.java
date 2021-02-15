package org.dom4j;

import java.util.Comparator;
import junit.textui.TestRunner;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.util.NodeComparator;

public class CloneTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    private Comparator comparator = new NodeComparator();

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.CloneTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testBug1148333() {
        DOMDocument dOMDocument = (DOMDocument) ((DOMDocumentFactory) DOMDocumentFactory.getInstance()).createDocument();
        dOMDocument.addElement("root").addNamespace("pref2", "uri2");
        DOMDocument dOMDocument2 = (DOMDocument) dOMDocument.cloneNode(true);
        assertNotSame(dOMDocument, dOMDocument2);
        assertNodesEqual((Document) dOMDocument, (Document) dOMDocument2);
    }

    public void testElementWithNamespaceClone() {
        Element createElement = DocumentFactory.getInstance().createElement("element");
        createElement.addNamespace("prefix", "uri");
        Element element = (Element) createElement.clone();
        assertNotSame(createElement, element);
        assertNodesEqual(createElement, element);
    }

    public void testDocumentClone() throws Exception {
        this.document.setName("doc1");
        Document document = (Document) this.document.clone();
        assertNotSame(this.document, document);
        assertNodesEqual(this.document, document);
    }

    public void testAddCloneToOtherElement() {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        Element addElement = createDocument.addElement("root");
        Element addElement2 = addElement.addElement("parent");
        Element addElement3 = addElement2.addElement("child");
        Element element = (Element) addElement2.clone();
        addElement.add(element);
        assertSame("parent not correct", addElement, element.getParent());
        assertSame("document not correct", createDocument, element.getDocument());
        Element element2 = element.element("child");
        assertNotSame("child not cloned", addElement3, element2);
        assertSame("parent not correct", element, element2.getParent());
        assertSame("document not correct", createDocument, element2.getDocument());
    }

    public void testRootElementClone() throws Exception {
        testElementClone(this.document.getRootElement());
    }

    public void testAuthorElementClone() throws Exception {
        testElementClone((Element) this.document.selectSingleNode("//author"));
    }

    public void testRootCompare1() throws Exception {
        Document document = (Document) this.document.clone();
        document.getRootElement().addAttribute("foo", "bar");
        assertTrue("Documents are not equal", this.comparator.compare(this.document, document) != 0);
    }

    public void testRootCompare2() throws Exception {
        Document document = (Document) this.document.clone();
        document.getRootElement().addText("foo");
        assertTrue("Documents are not equal", this.comparator.compare(this.document, document) != 0);
    }

    public void testAuthorCompare1() throws Exception {
        Document document = (Document) this.document.clone();
        ((Element) document.selectSingleNode("//author")).addAttribute("name", "James Strachan");
        assertTrue("Documents are not equal", this.comparator.compare(this.document, document) != 0);
    }

    public void testAuthorCompare2() throws Exception {
        Document document = (Document) this.document.clone();
        ((Element) document.selectSingleNode("//author")).addText("foo");
        assertTrue("Documents are not equal", this.comparator.compare(this.document, document) != 0);
    }

    /* access modifiers changed from: protected */
    public void testElementClone(Element element) throws Exception {
        Element element2 = (Element) element.clone();
        boolean z = true;
        assertTrue("Returned a new Element", element2 != element);
        assertNull("New element has no parent", element2.getParent());
        assertNull("New element has no Document", element2.getDocument());
        if (this.comparator.compare(element, element2) != 0) {
            z = false;
        }
        assertTrue("Element fragments are equal", z);
    }
}
