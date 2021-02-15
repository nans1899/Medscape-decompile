package org.dom4j;

import junit.textui.TestRunner;

public class ParseTextTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected String xmlText = "<root><author name='James'><location>Paris</location></author></root>";

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.ParseTextTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDocument() throws Exception {
        boolean z = true;
        assertTrue("Document is not null", this.document != null);
        Element rootElement = this.document.getRootElement();
        assertTrue("Root element is not null", rootElement != null);
        Element element = rootElement.element("author");
        if (element == null) {
            z = false;
        }
        assertTrue("Author element is not null", z);
        assertEquals("Name attribute matches", element.attributeValue("name"), "James");
        assertEquals("Location element matches", this.document.valueOf("/root/author/location"), "Paris");
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = DocumentHelper.parseText(this.xmlText);
    }
}
