package org.dom4j;

import com.facebook.internal.ServerProtocol;
import java.util.Iterator;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;

public class XPathExamplesTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected DocumentFactory factory = DocumentFactory.getInstance();
    protected Node testContext;
    protected Document testDocument;
    protected SAXReader xmlReader = new SAXReader();

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.XPathExamplesTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXPaths() throws Exception {
        Iterator elementIterator = getDocument("/xml/test/xpath/tests.xml").getRootElement().elementIterator("document");
        while (elementIterator.hasNext()) {
            testDocument((Element) elementIterator.next());
        }
    }

    /* access modifiers changed from: protected */
    public void testDocument(Element element) throws Exception {
        String attributeValue = element.attributeValue("url");
        this.testDocument = this.xmlReader.read(getFile(attributeValue));
        StringBuffer stringBuffer = new StringBuffer("Loaded test document: ");
        stringBuffer.append(attributeValue);
        assertTrue(stringBuffer.toString(), this.testDocument != null);
        StringBuffer stringBuffer2 = new StringBuffer("Loaded document: ");
        stringBuffer2.append(attributeValue);
        log(stringBuffer2.toString());
        Iterator elementIterator = element.elementIterator("context");
        while (elementIterator.hasNext()) {
            testContext(element, (Element) elementIterator.next());
        }
    }

    /* access modifiers changed from: protected */
    public void testContext(Element element, Element element2) throws Exception {
        String attributeValue = element2.attributeValue("select");
        List selectNodes = this.testDocument.selectNodes(attributeValue);
        StringBuffer stringBuffer = new StringBuffer("Found at least one context nodes to test for path: ");
        stringBuffer.append(attributeValue);
        assertTrue(stringBuffer.toString(), selectNodes != null && selectNodes.size() > 0);
        for (Object next : selectNodes) {
            StringBuffer stringBuffer2 = new StringBuffer("Context node is a Node: ");
            stringBuffer2.append(next);
            assertTrue(stringBuffer2.toString(), next instanceof Node);
            this.testContext = (Node) next;
            StringBuffer stringBuffer3 = new StringBuffer("Context is now: ");
            stringBuffer3.append(this.testContext);
            log(stringBuffer3.toString());
            runTests(element, element2);
            log("");
        }
    }

    /* access modifiers changed from: protected */
    public void runTests(Element element, Element element2) throws Exception {
        Iterator elementIterator = element2.elementIterator("test");
        while (elementIterator.hasNext()) {
            runTest(element, element2, (Element) elementIterator.next());
        }
        Iterator elementIterator2 = element2.elementIterator("valueOf");
        while (elementIterator2.hasNext()) {
            testValueOf(element, element2, (Element) elementIterator2.next());
        }
        Iterator elementIterator3 = element2.elementIterator("pattern");
        while (elementIterator3.hasNext()) {
            testPattern(element, element2, (Element) elementIterator3.next());
        }
        Iterator elementIterator4 = element2.elementIterator("fileter");
        while (elementIterator4.hasNext()) {
            testFilter(element, element2, (Element) elementIterator4.next());
        }
    }

    /* access modifiers changed from: protected */
    public void runTest(Element element, Element element2, Element element3) throws Exception {
        String attributeValue = element3.attributeValue("select");
        StringBuffer stringBuffer = new StringBuffer("Path: ");
        stringBuffer.append(attributeValue);
        String stringBuffer2 = stringBuffer.toString();
        String attributeValue2 = element3.attributeValue("exception");
        if (attributeValue2 != null && attributeValue2.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            try {
                this.testContext.selectNodes(attributeValue);
                fail("Exception was not thrown");
            } catch (XPathException unused) {
                return;
            }
        }
        String attributeValue3 = element3.attributeValue("count");
        if (attributeValue3 != null) {
            int parseInt = Integer.parseInt(attributeValue3);
            List selectNodes = this.testContext.selectNodes(attributeValue);
            StringBuffer stringBuffer3 = new StringBuffer(String.valueOf(stringBuffer2));
            stringBuffer3.append(" found result size: ");
            stringBuffer3.append(selectNodes.size());
            log(stringBuffer3.toString());
            StringBuffer stringBuffer4 = new StringBuffer(String.valueOf(stringBuffer2));
            stringBuffer4.append(" wrong result size");
            assertEquals(stringBuffer4.toString(), parseInt, selectNodes.size());
        }
        Element element4 = element3.element("valueOf");
        if (element4 != null) {
            Node selectSingleNode = this.testContext.selectSingleNode(attributeValue);
            StringBuffer stringBuffer5 = new StringBuffer(String.valueOf(stringBuffer2));
            stringBuffer5.append(" found node");
            assertTrue(stringBuffer5.toString(), selectSingleNode != null);
            String text = element4.getText();
            String valueOf = selectSingleNode.valueOf(element4.attributeValue("select"));
            log(stringBuffer2);
            StringBuffer stringBuffer6 = new StringBuffer("\texpected: ");
            stringBuffer6.append(text);
            stringBuffer6.append(" result: ");
            stringBuffer6.append(valueOf);
            log(stringBuffer6.toString());
            assertEquals(stringBuffer2, text, valueOf);
        }
    }

    /* access modifiers changed from: protected */
    public void testValueOf(Element element, Element element2, Element element3) throws Exception {
        String attributeValue = element3.attributeValue("select");
        StringBuffer stringBuffer = new StringBuffer("valueOf: ");
        stringBuffer.append(attributeValue);
        String stringBuffer2 = stringBuffer.toString();
        String text = element3.getText();
        String valueOf = this.testContext.valueOf(attributeValue);
        log(stringBuffer2);
        StringBuffer stringBuffer3 = new StringBuffer("\texpected: ");
        stringBuffer3.append(text);
        stringBuffer3.append(" result: ");
        stringBuffer3.append(valueOf);
        log(stringBuffer3.toString());
        assertEquals(stringBuffer2, text, valueOf);
    }

    /* access modifiers changed from: protected */
    public void testPattern(Element element, Element element2, Element element3) throws Exception {
        String attributeValue = element3.attributeValue("match");
        StringBuffer stringBuffer = new StringBuffer("match: ");
        stringBuffer.append(attributeValue);
        String stringBuffer2 = stringBuffer.toString();
        log("");
        log(stringBuffer2);
        assertTrue(stringBuffer2, this.factory.createPattern(attributeValue).matches(this.testContext));
    }

    /* access modifiers changed from: protected */
    public void testFilter(Element element, Element element2, Element element3) throws Exception {
        String attributeValue = element3.attributeValue("match");
        StringBuffer stringBuffer = new StringBuffer("match: ");
        stringBuffer.append(attributeValue);
        assertTrue(stringBuffer.toString(), this.testContext.matches(attributeValue));
    }
}
