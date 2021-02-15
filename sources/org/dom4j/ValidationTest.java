package org.dom4j;

import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;

public class ValidationTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.ValidationTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testValidation() throws Exception {
        try {
            new SAXReader(true).read("test");
            fail();
        } catch (DocumentException unused) {
        }
    }
}
