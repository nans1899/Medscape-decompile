package org.dom4j.xpath;

import java.io.File;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.DocumentHelper;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;

public class NamespaceTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {"namespace::*", "/Template/Application1/namespace::*", "/Template/Application1/namespace::xplt", "//namespace::*"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.NamespaceTest");
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
            assertTrue("Results should be Namespace objects", next instanceof Namespace);
            Namespace namespace = (Namespace) next;
            StringBuffer stringBuffer3 = new StringBuffer("Parent node: ");
            stringBuffer3.append(namespace.getParent());
            log(stringBuffer3.toString());
            assertTrue("Results should support the parent relationship", namespace.supportsParent());
            boolean z = true;
            assertTrue("Results should contain reference to the parent element", namespace.getParent() != null);
            if (namespace.getDocument() == null) {
                z = false;
            }
            assertTrue("Results should contain reference to the document", z);
        }
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = new SAXReader().read(new File("xml/testNamespaces.xml"));
    }
}
