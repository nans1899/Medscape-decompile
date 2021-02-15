package org.dom4j;

import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;
import org.dom4j.util.UserDataAttribute;
import org.dom4j.util.UserDataDocumentFactory;
import org.dom4j.util.UserDataElement;

public class UserDataTest extends AbstractTestCase {
    private static final String INPUT_XML_FILE = "/xml/web.xml";
    static /* synthetic */ Class class$0;
    private Object userData = new Double(1.23456d);

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.UserDataTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testSetData() throws Exception {
        Element rootElement = getRootElement();
        assertTrue("Element instanceof UserDataElement", rootElement instanceof UserDataElement);
        rootElement.setData(this.userData);
        boolean z = true;
        assertTrue("Stored user data!", rootElement.getData() == this.userData);
        StringBuffer stringBuffer = new StringBuffer("root: ");
        stringBuffer.append(rootElement);
        log(stringBuffer.toString());
        assertUserData(rootElement, this.userData);
        Element element = (Element) rootElement.clone();
        assertTrue("Cloned new instance", element != rootElement);
        assertUserData(element, this.userData);
        Element createCopy = rootElement.createCopy();
        if (createCopy == rootElement) {
            z = false;
        }
        assertTrue("Cloned new instance", z);
        assertUserData(createCopy, this.userData);
    }

    public void testCloneAttribute() throws Exception {
        Element rootElement = getRootElement();
        rootElement.addAttribute("name", "value");
        assertTrue(rootElement.attribute("name") instanceof UserDataAttribute);
        assertTrue(((Element) rootElement.clone()).attribute("name") instanceof UserDataAttribute);
    }

    public void testNewAdditions() throws Exception {
        Element rootElement = getRootElement();
        assertTrue("New Element is a UserDataElement", rootElement.addElement("foo1234") instanceof UserDataElement);
        rootElement.addAttribute("bar456", "123");
        assertTrue("New Attribute is a UserDataAttribute", rootElement.attribute("bar456") instanceof UserDataAttribute);
    }

    public void testNewDocument() throws Exception {
        Element addElement = UserDataDocumentFactory.getInstance().createDocument().addElement("root");
        assertTrue("Root Element is a UserDataElement", addElement instanceof UserDataElement);
        assertTrue("New Element is a UserDataElement", addElement.addElement("foo1234") instanceof UserDataElement);
        addElement.addAttribute("bar456", "123");
        assertTrue("New Attribute is a UserDataAttribute", addElement.attribute("bar456") instanceof UserDataAttribute);
    }

    /* access modifiers changed from: protected */
    public void assertUserData(Element element, Object obj) throws Exception {
        Object data = element.getData();
        assertTrue("No user data!", data != null);
        assertTrue("Stored user data correctly", obj.equals(data));
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        SAXReader sAXReader = new SAXReader();
        sAXReader.setDocumentFactory(UserDataDocumentFactory.getInstance());
        this.document = getDocument(INPUT_XML_FILE, sAXReader);
    }
}
