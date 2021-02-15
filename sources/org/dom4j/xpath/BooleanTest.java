package org.dom4j.xpath;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;

public class BooleanTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {".[name()='author']", ".[.='James Strachan']", ".[name()='XXXX']", ".[.='XXXX']", "name()='author'", "name()='XXXX'", ".='James Strachan'", ".='XXXX'"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.BooleanTest");
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
        XPath createXPath = DocumentHelper.createXPath(str);
        assertTrue("No xpath object was created", createXPath != null);
        StringBuffer stringBuffer = new StringBuffer("Evaluating xpath: ");
        stringBuffer.append(createXPath);
        log(stringBuffer.toString());
        for (Node testXPath : this.document.selectNodes("//author")) {
            testXPath(testXPath, createXPath);
        }
    }

    /* access modifiers changed from: protected */
    public void testXPath(Node node, XPath xPath) {
        xPath.selectNodes(node);
    }
}
