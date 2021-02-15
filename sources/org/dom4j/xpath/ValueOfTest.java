package org.dom4j.xpath;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Element;
import org.dom4j.Node;

public class ValueOfTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {"/root", "//author", "//author/@name", "/root/author[1]", "/root/author[1]/@name", "/root/author[2]", "/root/author[2]/@name", "/root/author[3]", "/root/author[3]/@name", "name()", "name(.)", "name(..)", "name(child::node())", "name(parent::*)", "name(../*)", "name(../child::node())", "local-name()", "local-name(..)", "local-name(parent::*)", "local-name(../*)", "parent::*", "name(/.)", "name(/child::node())", "name(/*)", ".", "..", "../*", "../child::node()", "/.", "/*", "*", "/child::node()"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.ValueOfTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXPaths() throws Exception {
        Element rootElement = this.document.getRootElement();
        testXPath(this.document);
        testXPath(rootElement);
        testXPath((Element) rootElement.elements("author").get(0));
    }

    /* access modifiers changed from: protected */
    public void testXPath(Node node) throws Exception {
        StringBuffer stringBuffer = new StringBuffer("Testing XPath on: ");
        stringBuffer.append(node);
        log(stringBuffer.toString());
        log("===============================");
        for (String testXPath : paths) {
            testXPath(node, testXPath);
        }
    }

    /* access modifiers changed from: protected */
    public void testXPath(Node node, String str) throws Exception {
        try {
            String valueOf = node.createXPath(str).valueOf(node);
            StringBuffer stringBuffer = new StringBuffer("valueOf: ");
            stringBuffer.append(str);
            stringBuffer.append(" is: ");
            stringBuffer.append(valueOf);
            log(stringBuffer.toString());
        } catch (Throwable th) {
            th.printStackTrace();
            StringBuffer stringBuffer2 = new StringBuffer("Failed with exception: ");
            stringBuffer2.append(th);
            assertTrue(stringBuffer2.toString(), false);
        }
    }
}
