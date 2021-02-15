package org.dom4j.dtd;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;

public class ExternalEntityDeclTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.dtd.ExternalEntityDeclTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testToString() {
        ExternalEntityDecl externalEntityDecl = new ExternalEntityDecl("name", (String) null, "systemID");
        ExternalEntityDecl externalEntityDecl2 = new ExternalEntityDecl("%name", (String) null, "systemID");
        assertEquals("<!ENTITY name SYSTEM \"systemID\" >", externalEntityDecl.toString());
        assertEquals("<!ENTITY % name SYSTEM \"systemID\" >", externalEntityDecl2.toString());
    }

    public void testSystemId() {
        ExternalEntityDecl externalEntityDecl = new ExternalEntityDecl("anEntity", (String) null, "http://www.myorg.org/foo");
        assertEquals("name is correct", "anEntity", externalEntityDecl.getName());
        assertEquals("publicID is correct", (String) null, externalEntityDecl.getPublicID());
        assertEquals("systemID is correct", "http://www.myorg.org/foo", externalEntityDecl.getSystemID());
        assertEquals("toString() is correct", "<!ENTITY anEntity SYSTEM \"http://www.myorg.org/foo\" >", externalEntityDecl.toString());
    }

    public void testPublicIdSystemId() {
        ExternalEntityDecl externalEntityDecl = new ExternalEntityDecl("anEntity", "-//dom4j//DTD sample", "http://www.myorg.org/foo");
        assertEquals("name is correct", "anEntity", externalEntityDecl.getName());
        assertEquals("publicID is correct", "-//dom4j//DTD sample", externalEntityDecl.getPublicID());
        assertEquals("systemID is correct", "http://www.myorg.org/foo", externalEntityDecl.getSystemID());
        assertEquals("toString() is correct", "<!ENTITY anEntity PUBLIC \"-//dom4j//DTD sample\" \"http://www.myorg.org/foo\" >", externalEntityDecl.toString());
    }
}
