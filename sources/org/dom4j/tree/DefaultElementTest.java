package org.dom4j.tree;

import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class DefaultElementTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.tree.DefaultElementTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testParentAfterSetContent() throws Exception {
        Document parseText = DocumentHelper.parseText("<root><a>a</a><b>b</b><x>x</x><d>d</d></root>");
        Node selectSingleNode = parseText.selectSingleNode("/root/x");
        List content = parseText.getRootElement().content();
        int indexOf = content.indexOf(selectSingleNode);
        Element createElement = DocumentHelper.createElement("c");
        createElement.setText("c");
        content.add(indexOf, createElement);
        assertNotNull(createElement.getParent());
        parseText.getRootElement().setContent(content);
        assertNotNull("Parent is null of setting content", createElement.getParent());
    }

    public void testGetStringValue() throws Exception {
        Document document = getDocument("xml/test/test_text.xml");
        assertEquals("String value incorrect", "This should work", document.getRootElement().getStringValue().trim());
        assertEquals("xpath value incorrect", "This should work", (String) document.selectObject("normalize-space(/message)"));
    }

    public void testBug894878() {
        Element createElement = DocumentFactory.getInstance().createElement("foo");
        createElement.addText("bla").addAttribute("foo", "bar");
        assertEquals("<foo foo=\"bar\">bla</foo>", createElement.asXML());
        Element createElement2 = DocumentFactory.getInstance().createElement("foo");
        createElement2.addAttribute("foo", "bar").addText("bla");
        assertEquals("<foo foo=\"bar\">bla</foo>", createElement2.asXML());
    }

    public void testGetNamespacesForURI() throws Exception {
        List namespacesForURI = DocumentHelper.parseText("<schema targetNamespace='http://SharedTest.org/xsd'         xmlns='http://www.w3.org/2001/XMLSchema'         xmlns:xsd='http://www.w3.org/2001/XMLSchema'>    <complexType name='AllStruct'>        <all>            <element name='arString' type='xsd:string'/>            <element name='varInt' type='xsd:int'/>        </all>    </complexType></schema>").getRootElement().getNamespacesForURI("http://www.w3.org/2001/XMLSchema");
        assertNotNull(namespacesForURI);
        assertEquals(2, namespacesForURI.size());
    }

    public void testDeclaredNamespaces() throws Exception {
        Element rootElement = DocumentHelper.parseText("<a xmlns:ns1=\"uri1\">    <ns1:b/>    <ns2:c xmlns:ns2=\"uri2\"/></a>").getRootElement();
        List declaredNamespaces = rootElement.declaredNamespaces();
        assertEquals(1, declaredNamespaces.size());
        assertSame(rootElement.getNamespaceForPrefix("ns1"), declaredNamespaces.get(0));
        assertEquals(0, rootElement.element("b").declaredNamespaces().size());
        Element element = rootElement.element("c");
        List declaredNamespaces2 = element.declaredNamespaces();
        assertEquals(1, declaredNamespaces2.size());
        assertSame(element.getNamespaceForPrefix("ns2"), declaredNamespaces2.get(0));
    }

    public void testAdditionalNamespaces() throws Exception {
        Element rootElement = DocumentHelper.parseText("<a xmlns:ns1=\"uri1\">    <ns1:b/>    <ns2:c xmlns:ns2=\"uri2\"/></a>").getRootElement();
        List additionalNamespaces = rootElement.additionalNamespaces();
        assertEquals(1, additionalNamespaces.size());
        assertSame(rootElement.getNamespaceForPrefix("ns1"), additionalNamespaces.get(0));
        assertEquals(0, rootElement.element("b").additionalNamespaces().size());
        assertEquals(0, rootElement.element("c").additionalNamespaces().size());
    }
}
