package org.dom4j;

import junit.textui.TestRunner;

public class AddAttributeTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.AddAttributeTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testAddAttributeNormalValue() throws Exception {
        Node selectSingleNode = this.document.selectSingleNode("//root/author[1]");
        assertTrue(selectSingleNode instanceof Element);
        Element element = (Element) selectSingleNode;
        element.addAttribute("testAtt", "testValue");
        assertEquals(3, element.attributeCount());
        assertEquals("testValue", element.attributeValue("testAtt"));
    }

    public void testAddAttributeNullValue() throws Exception {
        Node selectSingleNode = this.document.selectSingleNode("//root/author[1]");
        assertTrue(selectSingleNode instanceof Element);
        Element element = (Element) selectSingleNode;
        element.addAttribute("location", (String) null);
        assertEquals(1, element.attributeCount());
        assertNull(element.attributeValue("location"));
    }
}
