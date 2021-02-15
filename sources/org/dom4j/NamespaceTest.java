package org.dom4j;

import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;

public class NamespaceTest extends AbstractTestCase {
    private static final String INPUT_XML_FILE = "/xml/namespaces.xml";
    private static final Namespace XSL_NAMESPACE;
    private static final QName XSL_TEMPLATE;
    static /* synthetic */ Class class$0;

    static {
        Namespace namespace = Namespace.get("xsl", "http://www.w3.org/1999/XSL/Transform");
        XSL_NAMESPACE = namespace;
        XSL_TEMPLATE = QName.get(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE, namespace);
    }

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.NamespaceTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void debugShowNamespaces() throws Exception {
        Iterator elementIterator = getRootElement().elementIterator();
        while (elementIterator.hasNext()) {
            Element element = (Element) elementIterator.next();
            StringBuffer stringBuffer = new StringBuffer("Found element:    ");
            stringBuffer.append(element);
            log(stringBuffer.toString());
            StringBuffer stringBuffer2 = new StringBuffer("Namespace:        ");
            stringBuffer2.append(element.getNamespace());
            log(stringBuffer2.toString());
            StringBuffer stringBuffer3 = new StringBuffer("Namespace prefix: ");
            stringBuffer3.append(element.getNamespacePrefix());
            log(stringBuffer3.toString());
            StringBuffer stringBuffer4 = new StringBuffer("Namespace URI:    ");
            stringBuffer4.append(element.getNamespaceURI());
            log(stringBuffer4.toString());
        }
    }

    public void testGetElement() throws Exception {
        Element element = getRootElement().element(XSL_TEMPLATE);
        assertTrue("Root element contains at least one <xsl:template/> element", element != null);
        StringBuffer stringBuffer = new StringBuffer("Found element: ");
        stringBuffer.append(element);
        log(stringBuffer.toString());
    }

    public void testGetElements() throws Exception {
        List elements = getRootElement().elements(XSL_TEMPLATE);
        assertTrue("Root element contains at least one <xsl:template/> element", elements.size() > 0);
        StringBuffer stringBuffer = new StringBuffer("Found elements: ");
        stringBuffer.append(elements);
        log(stringBuffer.toString());
    }

    public void testElementIterator() throws Exception {
        Iterator elementIterator = getRootElement().elementIterator(XSL_TEMPLATE);
        assertTrue("Root element contains at least one <xsl:template/> element", elementIterator.hasNext());
        do {
            StringBuffer stringBuffer = new StringBuffer("Found element: ");
            stringBuffer.append((Element) elementIterator.next());
            log(stringBuffer.toString());
        } while (elementIterator.hasNext());
    }

    public void testNamespaceUriMap() throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("x", "fooNamespace");
        hashMap.put("y", "barNamespace");
        DocumentFactory documentFactory = new DocumentFactory();
        documentFactory.setXPathNamespaceURIs(hashMap);
        SAXReader sAXReader = new SAXReader();
        sAXReader.setDocumentFactory(documentFactory);
        String valueOf = getDocument("/xml/test/nestedNamespaces.xml", sAXReader).valueOf("/x:pizza/y:cheese/x:pepper");
        StringBuffer stringBuffer = new StringBuffer("Found value: ");
        stringBuffer.append(valueOf);
        log(stringBuffer.toString());
        assertEquals("XPath used default namesapce URIS", "works", valueOf);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = getDocument(INPUT_XML_FILE);
    }

    /* access modifiers changed from: protected */
    public Element getRootElement() {
        Element rootElement = this.document.getRootElement();
        assertTrue("Document has root element", rootElement != null);
        return rootElement;
    }
}
