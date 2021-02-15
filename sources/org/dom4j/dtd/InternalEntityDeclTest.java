package org.dom4j.dtd;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;

public class InternalEntityDeclTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.dtd.InternalEntityDeclTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testToString() {
        InternalEntityDecl internalEntityDecl = new InternalEntityDecl("name", "value");
        InternalEntityDecl internalEntityDecl2 = new InternalEntityDecl("%name", "value");
        assertEquals("<!ENTITY name \"value\">", internalEntityDecl.toString());
        assertEquals("<!ENTITY % name \"value\">", internalEntityDecl2.toString());
    }

    public void testParameterEntity() {
        InternalEntityDecl internalEntityDecl = new InternalEntityDecl("%boolean", "( true | false )");
        assertEquals("name is correct", "%boolean", internalEntityDecl.getName());
        assertEquals("value is correct", "( true | false )", internalEntityDecl.getValue());
        assertEquals("toString() is correct", "<!ENTITY % boolean \"( true | false )\">", internalEntityDecl.toString());
    }

    public void testGeneralEntity() {
        InternalEntityDecl internalEntityDecl = new InternalEntityDecl("foo", "bar");
        assertEquals("name is correct", "foo", internalEntityDecl.getName());
        assertEquals("value is correct", "bar", internalEntityDecl.getValue());
        assertEquals("toString() is correct", "<!ENTITY foo \"bar\">", internalEntityDecl.toString());
    }
}
