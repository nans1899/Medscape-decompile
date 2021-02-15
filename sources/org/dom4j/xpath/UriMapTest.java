package org.dom4j.xpath;

import java.io.File;
import java.util.HashMap;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class UriMapTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.UriMapTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testURIMap() throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
        hashMap.put("m", "urn:xmethodsBabelFish");
        XPath createXPath = this.document.createXPath("/SOAP-ENV:Envelope/SOAP-ENV:Body/m:BabelFish");
        createXPath.setNamespaceURIs(hashMap);
        assertTrue("Found valid node", createXPath.selectSingleNode(this.document) != null);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = new SAXReader().read(new File("xml/soap.xml"));
    }
}
