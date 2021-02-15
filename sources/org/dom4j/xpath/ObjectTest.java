package org.dom4j.xpath;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Node;

public class ObjectTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {"name(/.)", "name()"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.ObjectTest");
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
    }

    /* access modifiers changed from: protected */
    public void testXPath(Node node, String str) {
        node.createXPath(str).evaluate(node);
    }
}
