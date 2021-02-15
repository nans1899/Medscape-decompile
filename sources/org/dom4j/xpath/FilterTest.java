package org.dom4j.xpath;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.NodeFilter;

public class FilterTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {".[name()='author']", ".[name()='XXXX']", ".[.='James Strachan']", ".[.='XXXX']"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.FilterTest");
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
        NodeFilter createXPathFilter = DocumentHelper.createXPathFilter(str);
        assertTrue("No NodeFilter object was created", createXPathFilter != null);
        for (Node node : this.document.selectNodes("//author")) {
            if (createXPathFilter.matches(node)) {
                StringBuffer stringBuffer = new StringBuffer("Matches node: ");
                stringBuffer.append(node.asXML());
                log(stringBuffer.toString());
            } else {
                StringBuffer stringBuffer2 = new StringBuffer("No match for node: ");
                stringBuffer2.append(node.asXML());
                log(stringBuffer2.toString());
            }
        }
    }
}
