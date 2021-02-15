package org.dom4j;

import com.appboy.Constants;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import junit.textui.TestRunner;
import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;

public class NamespacesTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.NamespacesTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testNamespaces() throws Exception {
        testNamespaces(this.document);
        testNamespaces(saxRoundTrip(this.document));
        testNamespaces(domRoundTrip(this.document));
    }

    public void testNamespaces(Document document) throws Exception {
        Element rootElement = ((Document) document.clone()).getRootElement();
        assertNamespace(rootElement.getNamespace(), "", "http://www.w3.org/2001/XMLSchema");
        assertEquals("xmlns=\"http://www.w3.org/2001/XMLSchema\"", rootElement.getNamespace().asXML());
        assertEquals("namespace::*[name()='']", rootElement.getNamespace().getPath());
        assertEquals("namespace::*[name()='']", rootElement.getNamespace().getUniquePath());
        List additionalNamespaces = rootElement.additionalNamespaces();
        boolean z = true;
        assertTrue("at least one additional namespace", additionalNamespaces != null && additionalNamespaces.size() > 0);
        Namespace namespace = (Namespace) additionalNamespaces.get(0);
        assertNamespace(namespace, Constants.APPBOY_PUSH_TITLE_KEY, "http://www.w3.org/namespace/");
        assertEquals("xmlns:t=\"http://www.w3.org/namespace/\"", namespace.asXML());
        assertEquals("namespace::t", namespace.getPath());
        assertEquals("namespace::t", namespace.getUniquePath());
        assertTrue("First node is a namespace", rootElement.node(0) instanceof Namespace);
        rootElement.remove(namespace);
        rootElement.addNamespace(Constants.APPBOY_PUSH_TITLE_KEY, "myNewURI");
        List additionalNamespaces2 = rootElement.additionalNamespaces();
        assertTrue("at least one additional namespace", additionalNamespaces2 != null && additionalNamespaces2.size() > 0);
        assertNamespace((Namespace) additionalNamespaces2.get(0), Constants.APPBOY_PUSH_TITLE_KEY, "myNewURI");
        additionalNamespaces2.remove(0);
        additionalNamespaces2.add(Namespace.get(Constants.APPBOY_PUSH_TITLE_KEY, "myNewURI-2"));
        List additionalNamespaces3 = rootElement.additionalNamespaces();
        assertTrue("at least one additional namespace", additionalNamespaces3 != null && additionalNamespaces3.size() > 0);
        assertNamespace((Namespace) additionalNamespaces3.get(0), Constants.APPBOY_PUSH_TITLE_KEY, "myNewURI-2");
        additionalNamespaces3.clear();
        rootElement.addNamespace(Constants.APPBOY_PUSH_TITLE_KEY, "myNewURI");
        List additionalNamespaces4 = rootElement.additionalNamespaces();
        if (additionalNamespaces4 == null || additionalNamespaces4.size() <= 0) {
            z = false;
        }
        assertTrue("at least one additional namespace", z);
        assertNamespace((Namespace) additionalNamespaces4.get(0), Constants.APPBOY_PUSH_TITLE_KEY, "myNewURI");
        StringBuffer stringBuffer = new StringBuffer("Namespaces: ");
        stringBuffer.append(additionalNamespaces4);
        log(stringBuffer.toString());
        log("XML is now");
        log(rootElement.asXML());
    }

    public void testNamespaceForPrefix() throws Exception {
        testNamespaceForPrefix(this.document);
        testNamespaceForPrefix(saxRoundTrip(this.document));
        testNamespaceForPrefix(domRoundTrip(this.document));
    }

    public void testNamespaceForPrefix(Document document) throws Exception {
        Element rootElement = document.getRootElement();
        Namespace namespaceForPrefix = rootElement.getNamespaceForPrefix(Constants.APPBOY_PUSH_TITLE_KEY);
        assertNamespace(namespaceForPrefix, Constants.APPBOY_PUSH_TITLE_KEY, "http://www.w3.org/namespace/");
        boolean z = false;
        Namespace namespaceForPrefix2 = ((Element) rootElement.elements().get(0)).getNamespaceForPrefix(Constants.APPBOY_PUSH_TITLE_KEY);
        assertNamespace(namespaceForPrefix2, Constants.APPBOY_PUSH_TITLE_KEY, "http://www.w3.org/namespace/");
        if (namespaceForPrefix == namespaceForPrefix2) {
            z = true;
        }
        assertTrue("Same namespace instance returned", z);
        StringBuffer stringBuffer = new StringBuffer("found: ");
        stringBuffer.append(namespaceForPrefix.asXML());
        log(stringBuffer.toString());
    }

    public void testNamespaceForDefaultPrefix() throws Exception {
        Document document = getDocument("/xml/test/defaultNamespace.xml");
        testNamespaceForDefaultPrefix(document);
        testNamespaceForDefaultPrefix(saxRoundTrip(document));
        testNamespaceForDefaultPrefix(domRoundTrip(document));
    }

    public void testNamespaceForDefaultPrefix(Document document) throws Exception {
        for (Element element : document.selectNodes("//*")) {
            assertNamespace(element.getNamespaceForPrefix(""), "", "dummyNamespace");
            Namespace namespaceForPrefix = element.getNamespaceForPrefix((String) null);
            assertNamespace(namespaceForPrefix, "", "dummyNamespace");
            StringBuffer stringBuffer = new StringBuffer("found: ");
            stringBuffer.append(namespaceForPrefix.asXML());
            log(stringBuffer.toString());
        }
    }

    public void testAttributeDefaultPrefix() throws Exception {
        Document document = getDocument("/xml/test/soap3.xml");
        testAttributeDefaultPrefix(document);
        testAttributeDefaultPrefix(saxRoundTrip(document));
        testAttributeDefaultPrefix(domRoundTrip(document));
    }

    public void testAttributeDefaultPrefix(Document document) throws Exception {
        List<Attribute> selectNodes = document.selectNodes("//@*[local-name()='actor']");
        assertTrue("Matched at least one 'actor' attribute", selectNodes.size() > 0);
        for (Attribute attribute : selectNodes) {
            StringBuffer stringBuffer = new StringBuffer("found: ");
            stringBuffer.append(attribute.asXML());
            log(stringBuffer.toString());
            Element parent = attribute.getParent();
            assertTrue("Attribute has a parent", parent != null);
            assertNamespace(parent.getNamespaceForPrefix(""), "", "http://schemas.xmlsoap.org/soap/envelope/");
            assertNamespace(attribute.getNamespace(), "", "");
        }
    }

    public void testNamespaceForURI() throws Exception {
        testNamespaceForURI(this.document);
        testNamespaceForURI(saxRoundTrip(this.document));
        testNamespaceForURI(domRoundTrip(this.document));
    }

    public void testNamespaceForURI(Document document) throws Exception {
        Element rootElement = document.getRootElement();
        Namespace namespaceForURI = rootElement.getNamespaceForURI("http://www.w3.org/namespace/");
        assertNamespace(namespaceForURI, Constants.APPBOY_PUSH_TITLE_KEY, "http://www.w3.org/namespace/");
        boolean z = false;
        Namespace namespaceForURI2 = ((Element) rootElement.elements().get(0)).getNamespaceForURI("http://www.w3.org/namespace/");
        assertNamespace(namespaceForURI2, Constants.APPBOY_PUSH_TITLE_KEY, "http://www.w3.org/namespace/");
        if (namespaceForURI == namespaceForURI2) {
            z = true;
        }
        assertTrue("Same namespace instance returned", z);
        StringBuffer stringBuffer = new StringBuffer("found: ");
        stringBuffer.append(namespaceForURI.asXML());
        log(stringBuffer.toString());
    }

    public void testRedeclareNamespaces() throws Exception {
        Document document = getDocument("/xml/test/soap2.xml");
        testRedeclareNamespaces(document);
        testRedeclareNamespaces(saxRoundTrip(document));
        testRedeclareNamespaces(domRoundTrip(document));
    }

    public void testRedeclareNamespaces(Document document) throws Exception {
        assertNamespaces(document.selectNodes("//*[local-name()='Envelope']"), "SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
        assertNamespaces(document.selectNodes("//*[local-name()='Body']"), "SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
        assertNamespaces(document.selectNodes("//*[local-name()='bar']"), Constants.APPBOY_PUSH_CONTENT_KEY, "barURI");
        assertNamespaces(document.selectNodes("//*[local-name()='newBar']"), Constants.APPBOY_PUSH_CONTENT_KEY, "newBarURI");
        assertNamespaces(document.selectNodes("//*[local-name()='foo']"), "", "fooURI");
        assertNamespaces(document.selectNodes("//*[local-name()='newFoo']"), "", "newFooURI");
    }

    public void testDefaultNamespaceIssue() throws Exception {
        Document document = getDocument("/xml/test/defaultNamespaceIssue.xsd");
        testDefaultNamespaceIssue(document);
        testDefaultNamespaceIssue(saxRoundTrip(document));
        testDefaultNamespaceIssue(domRoundTrip(document));
    }

    public void testDefaultNamespaceIssue(Document document) throws Exception {
        assertNotNull("default namespace redeclaration", (Element) document.selectSingleNode("/xsd:schema/xsd:element/xsd:annotation/xsd:documentation/text"));
        for (Namespace namespace : document.getRootElement().declaredNamespaces()) {
            if ("urn:wapforum:devicesheet".equals(namespace.getURI()) && "".equals(namespace.getPrefix())) {
                return;
            }
        }
        fail("Default namespace declaration not present on root element");
    }

    public void testDefaultNamespace() throws Exception {
        Document createDocument = DocumentHelper.createDocument();
        Element addElement = createDocument.addElement("process-definition", "http://jbpm.org/statedefinition-2.0-beta3").addElement("start-state");
        addElement.addAttribute("name", "start");
        addElement.addElement("transition").addAttribute("to", JSONAPISpecConstants.FIRST);
        assertEquals("http://jbpm.org/statedefinition-2.0-beta3", addElement.getNamespace().getURI());
        assertEquals("", addElement.getNamespace().getPrefix());
        System.out.println(createDocument.asXML());
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = getDocument("/xml/test/test_schema.xml");
    }

    /* access modifiers changed from: protected */
    public Document saxRoundTrip(Document document) throws Exception {
        return DocumentHelper.parseText(document.asXML());
    }

    /* access modifiers changed from: protected */
    public Document domRoundTrip(Document document) throws Exception {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        return new DOMReader().read(newInstance.newDocumentBuilder().parse(new InputSource(new StringReader(document.asXML()))));
    }

    /* access modifiers changed from: protected */
    public void assertNamespaces(List list, String str, String str2) throws Exception {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            assertNamespace(((Element) it.next()).getNamespace(), str, str2);
        }
    }

    /* access modifiers changed from: protected */
    public void assertNamespace(Namespace namespace, String str, String str2) throws Exception {
        assertEquals("namespace prefix", str, namespace.getPrefix());
        assertEquals("namespace URI", str2, namespace.getURI());
    }
}
