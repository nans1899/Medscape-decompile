package org.dom4j.xpath;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.Element;

public class SelectSingleNodeTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.SelectSingleNodeTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testSelectSingleNode() throws Exception {
        Document document = getDocument("/xml/test/jimBrain.xml");
        boolean z = true;
        assertTrue("Found a valid node", document.selectSingleNode("/properties/client/threadsafe") != null);
        assertTrue("Found a valid server", ((Element) document.selectSingleNode("/properties/server")) != null);
        assertTrue("Found a valid server", ((Element) document.getRootElement().selectSingleNode("/properties/server")) != null);
        Element element = (Element) document.selectSingleNode("properties/server");
        assertTrue("Found a valid server", element != null);
        if (((Element) element.selectSingleNode("db/connection")) == null) {
            z = false;
        }
        assertTrue("Found a valid connection", z);
    }

    public void testSteensBug() throws Exception {
        Document document = getDocument("/xml/schema/personal.xsd");
        assertNotNull("element is null", document.selectSingleNode("/xs:schema/xs:element[@name='person']"));
        assertNotNull("element is null", document.getRootElement().selectSingleNode("/xs:schema/xs:element[@name='person']"));
    }
}
