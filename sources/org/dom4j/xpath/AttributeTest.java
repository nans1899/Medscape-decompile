package org.dom4j.xpath;

import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;

public class AttributeTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {"attribute::*", "/root/author/attribute::*", "//attribute::*", "@name"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.AttributeTest");
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
        List selectNodes = DocumentHelper.createXPath(str).selectNodes(this.document);
        StringBuffer stringBuffer = new StringBuffer("Searched path: ");
        stringBuffer.append(str);
        stringBuffer.append(" found: ");
        stringBuffer.append(selectNodes.size());
        stringBuffer.append(" result(s)");
        log(stringBuffer.toString());
        for (Object next : selectNodes) {
            StringBuffer stringBuffer2 = new StringBuffer("Found Result: ");
            stringBuffer2.append(next);
            log(stringBuffer2.toString());
            assertTrue("Results should be Attribute objects", next instanceof Attribute);
            Attribute attribute = (Attribute) next;
            assertTrue("Results should support the parent relationship", attribute.supportsParent());
            boolean z = true;
            assertTrue("Results should contain reference to the parent element", attribute.getParent() != null);
            if (attribute.getDocument() == null) {
                z = false;
            }
            assertTrue("Resulting document not correct", z);
        }
    }
}
