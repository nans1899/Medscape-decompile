package org.dom4j;

import junit.textui.TestRunner;

public class NullAttributesTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected Document document;
    protected Element element;
    protected DocumentFactory factory;

    public NullAttributesTest() {
        DocumentFactory instance = DocumentFactory.getInstance();
        this.factory = instance;
        Document createDocument = instance.createDocument();
        this.document = createDocument;
        this.element = createDocument.addElement("root");
    }

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.NullAttributesTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testStringNames() throws Exception {
        this.element.addAttribute("foo", (String) null);
        boolean z = true;
        assertTrue(this.element.attribute("foo") == null);
        this.element.addAttribute("foo", "123");
        assertTrue(this.element.attribute("foo") != null);
        this.element.addAttribute("foo", (String) null);
        if (this.element.attribute("foo") != null) {
            z = false;
        }
        assertTrue(z);
    }

    public void testQNames() throws Exception {
        QName qName = QName.get("bar");
        this.element.addAttribute(qName, (String) null);
        boolean z = true;
        assertTrue(this.element.attribute(qName) == null);
        this.element.addAttribute(qName, "123");
        assertTrue(this.element.attribute(qName) != null);
        this.element.addAttribute(qName, (String) null);
        if (this.element.attribute(qName) != null) {
            z = false;
        }
        assertTrue(z);
    }

    public void testAttributes() throws Exception {
        Attribute createAttribute = this.factory.createAttribute(this.element, "v", (String) null);
        boolean z = true;
        assertTrue(createAttribute.getText() == null);
        assertTrue(createAttribute.getValue() == null);
        this.element.add(createAttribute);
        assertTrue(this.element.attribute("v") == null);
        this.element.add(this.factory.createAttribute(this.element, "v", "123"));
        assertTrue(this.element.attribute("v") != null);
        this.element.add(this.factory.createAttribute(this.element, "v", (String) null));
        if (this.element.attribute("v") != null) {
            z = false;
        }
        assertTrue(z);
    }
}
