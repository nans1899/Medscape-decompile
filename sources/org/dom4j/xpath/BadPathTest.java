package org.dom4j.xpath;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;

public class BadPathTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    private String[] paths = {"+", "/foo/bar/"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.BadPathTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testBadPaths() throws Exception {
        for (String testBadPath : this.paths) {
            testBadPath(testBadPath);
        }
    }

    /* access modifiers changed from: protected */
    public void testBadPath(String str) throws Exception {
        try {
            this.document.selectObject(str);
            StringBuffer stringBuffer = new StringBuffer("Should have thrown exception for: ");
            stringBuffer.append(str);
            fail(stringBuffer.toString());
        } catch (Exception e) {
            StringBuffer stringBuffer2 = new StringBuffer("Successfully caught: ");
            stringBuffer2.append(e);
            log(stringBuffer2.toString());
        }
        try {
            this.document.createXPath(str);
            StringBuffer stringBuffer3 = new StringBuffer("Should have thrown exception for: ");
            stringBuffer3.append(str);
            fail(stringBuffer3.toString());
        } catch (Exception e2) {
            StringBuffer stringBuffer4 = new StringBuffer("Successfully caught: ");
            stringBuffer4.append(e2);
            log(stringBuffer4.toString());
        }
    }
}
