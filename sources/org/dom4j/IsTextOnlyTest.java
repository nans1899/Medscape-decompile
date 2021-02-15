package org.dom4j;

import junit.textui.TestRunner;

public class IsTextOnlyTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.IsTextOnlyTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDocument() throws Exception {
        Element createElement = new DocumentFactory().createElement("root");
        Element addElement = createElement.addElement("child");
        addElement.addText("This is some text");
        StringBuffer stringBuffer = new StringBuffer("Root node is not text only: ");
        stringBuffer.append(createElement);
        assertTrue(stringBuffer.toString(), !createElement.isTextOnly());
        StringBuffer stringBuffer2 = new StringBuffer("First child is text only: ");
        stringBuffer2.append(addElement);
        assertTrue(stringBuffer2.toString(), addElement.isTextOnly());
    }
}
