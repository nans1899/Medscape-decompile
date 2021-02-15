package org.dom4j;

import java.util.List;
import junit.textui.TestRunner;

public class AttributeDetachTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.AttributeDetachTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDetachAttribute() throws Exception {
        List<Attribute> selectNodes = this.document.selectNodes("//@name");
        assertTrue("Found more than one attribute: ", selectNodes.size() > 0);
        for (Attribute attribute : selectNodes) {
            Element parent = attribute.getParent();
            StringBuffer stringBuffer = new StringBuffer("Attribute: ");
            stringBuffer.append(attribute);
            stringBuffer.append(" has parent: ");
            stringBuffer.append(parent);
            assertTrue(stringBuffer.toString(), attribute.getParent() == parent);
            QName qName = attribute.getQName();
            parent.attribute(qName);
            assertEquals("Attribute and Element have same attrbute value", attribute.getValue(), parent.attributeValue(qName));
            attribute.detach();
            Attribute attribute2 = parent.attribute(qName);
            String attributeValue = parent.attributeValue(qName);
            StringBuffer stringBuffer2 = new StringBuffer("Element now has no value: ");
            stringBuffer2.append(attributeValue);
            assertTrue(stringBuffer2.toString(), attributeValue == null);
            StringBuffer stringBuffer3 = new StringBuffer("Element now has no attribute: ");
            stringBuffer3.append(attribute2);
            assertTrue(stringBuffer3.toString(), attribute2 == null);
        }
    }
}
