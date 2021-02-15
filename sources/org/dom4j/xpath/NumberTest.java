package org.dom4j.xpath;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Node;

public class NumberTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {"2+2", "2 + 2", "2 + number(1) + 2", "number(1) * 2", "2 + count(//author) + 2", "2 + (2 * 5)", "count(//author) + count(//author/attribute::*)", "(12 + count(//author) + count(//author/attribute::*)) div 2", "count(//author)", "count(//author/attribute::*)", "2 + number(1) * 2", "count(descendant::author)", "count(ancestor::author)", "count(descendant::*)", "count(descendant::author)+1", "count(ancestor::*)", "10 + count(ancestor-or-self::author) + 5", "10 + count(descendant::author) * 5", "10 + (count(descendant::author) * 5)"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.NumberTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXPaths() throws Exception {
        Node selectSingleNode = this.document.selectSingleNode("//author");
        int length = paths.length;
        for (int i = 0; i < length; i++) {
            testXPath(this.document, paths[i]);
            testXPath(selectSingleNode, paths[i]);
        }
        log("Finished successfully");
    }

    /* access modifiers changed from: protected */
    public void testXPath(Node node, String str) throws Exception {
        try {
            Number numberValueOf = node.createXPath(str).numberValueOf(node);
            StringBuffer stringBuffer = new StringBuffer("Searched path: ");
            stringBuffer.append(str);
            stringBuffer.append(" found: ");
            stringBuffer.append(numberValueOf);
            log(stringBuffer.toString());
        } catch (Throwable th) {
            StringBuffer stringBuffer2 = new StringBuffer("Caught exception: ");
            stringBuffer2.append(th);
            log(stringBuffer2.toString());
            th.printStackTrace();
            StringBuffer stringBuffer3 = new StringBuffer("Failed to process:  ");
            stringBuffer3.append(str);
            stringBuffer3.append(" caught exception: ");
            stringBuffer3.append(th);
            assertTrue(stringBuffer3.toString(), false);
        }
    }
}
