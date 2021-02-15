package org.dom4j.xpath;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Text;

public class TextTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {"text()", "//author/text()"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.TextTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXPaths() throws Exception {
        for (String testXPath : paths) {
            testXPath(testXPath);
        }
    }

    /* access modifiers changed from: protected */
    public void testXPath(String str) {
        for (Object next : this.document.selectNodes(str)) {
            StringBuffer stringBuffer = new StringBuffer("Found Result: ");
            stringBuffer.append(next);
            log(stringBuffer.toString());
            assertTrue("Results not Text objects", next instanceof Text);
            Text text = (Text) next;
            assertTrue("Results should support the parent relationship", text.supportsParent());
            boolean z = true;
            assertTrue("Results should contain reference to the parent element", text.getParent() != null);
            if (text.getDocument() == null) {
                z = false;
            }
            assertTrue("Results should not reference to the owning document", z);
        }
    }
}
