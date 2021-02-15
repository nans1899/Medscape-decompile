package org.dom4j.dtd;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;

public class ElementDeclTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.dtd.ElementDeclTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testSimpleElementDecl() {
        ElementDecl elementDecl = new ElementDecl("an-element", "(#PCDATA)");
        assertEquals("name is correct", "an-element", elementDecl.getName());
        assertEquals("model is correct", "(#PCDATA)", elementDecl.getModel());
        assertEquals("toString() is correct", "<!ELEMENT an-element (#PCDATA)>", elementDecl.toString());
    }
}
