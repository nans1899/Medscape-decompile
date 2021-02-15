package org.dom4j.dtd;

import com.wbmd.wbmddrugscommons.constants.Constants;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;

public class AttributeDeclTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.dtd.AttributeDeclTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testIdImpliedNone() {
        assertSameAttributeDecl(new MyTestAttributeDecl("foo", "bar", Constants.WBMDTugStringID, "#IMPLIED", (String) null, "<!ATTLIST foo bar ID #IMPLIED>"), new AttributeDecl("foo", "bar", Constants.WBMDTugStringID, "#IMPLIED", (String) null));
    }

    public void testCDataFixedValue() {
        assertSameAttributeDecl(new MyTestAttributeDecl("foo", "bar", "CDATA", "#FIXED", "goo", "<!ATTLIST foo bar CDATA #FIXED \"goo\">"), new AttributeDecl("foo", "bar", "CDATA", "#FIXED", "goo"));
    }

    public void testCDataNoneValue() {
        assertSameAttributeDecl(new MyTestAttributeDecl("foo", "bar", "CDATA", (String) null, "goo", "<!ATTLIST foo bar CDATA \"goo\">"), new AttributeDecl("foo", "bar", "CDATA", (String) null, "goo"));
    }

    /* access modifiers changed from: protected */
    public void assertSameAttributeDecl(MyTestAttributeDecl myTestAttributeDecl, AttributeDecl attributeDecl) {
        assertEquals("elementName is correct", myTestAttributeDecl.getElementName(), attributeDecl.getElementName());
        assertEquals("attributeName is correct", myTestAttributeDecl.getAttributeName(), attributeDecl.getAttributeName());
        assertEquals("type is correct", myTestAttributeDecl.getType(), attributeDecl.getType());
        assertEquals("valueDefault is correct", myTestAttributeDecl.getValueDefault(), attributeDecl.getValueDefault());
        assertEquals("toString() is correct", myTestAttributeDecl.getText(), attributeDecl.toString());
    }

    protected static class MyTestAttributeDecl {
        private String attName;
        private String declType;
        private String declValue;
        private String defaultValue;
        private String elName;
        private String txt;

        public MyTestAttributeDecl(String str, String str2, String str3, String str4, String str5, String str6) {
            this.elName = str;
            this.attName = str2;
            this.declType = str3;
            this.defaultValue = str4;
            this.declValue = str5;
            this.txt = str6;
        }

        public String getElementName() {
            return this.elName;
        }

        public String getAttributeName() {
            return this.attName;
        }

        public String getType() {
            return this.declType;
        }

        public String getValueDefault() {
            return this.defaultValue;
        }

        public String getValue() {
            return this.declValue;
        }

        public String getText() {
            return this.txt;
        }
    }
}
