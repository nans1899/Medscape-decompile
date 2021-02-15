package org.dom4j.xpath;

import java.io.File;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.DocumentHelper;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.jaxen.SimpleNamespaceContext;

public class PrefixTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {"//xplt:anyElement", "//xpl:insertText", "/Template/Application1/xpl:insertText", "/Template/Application2/xpl:insertText"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.PrefixTest");
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
        SimpleNamespaceContext simpleNamespaceContext = new SimpleNamespaceContext();
        simpleNamespaceContext.addNamespace("xplt", "www.xxxx.com");
        simpleNamespaceContext.addNamespace("xpl", "www.xxxx.com");
        createXPath.setNamespaceContext(simpleNamespaceContext);
        List selectNodes = createXPath.selectNodes(this.document);
        StringBuffer stringBuffer = new StringBuffer("Searched path: ");
        stringBuffer.append(str);
        stringBuffer.append(" found: ");
        stringBuffer.append(selectNodes.size());
        stringBuffer.append(" result(s)");
        log(stringBuffer.toString());
        assertTrue("Should have found at lest one result", selectNodes.size() > 0);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = new SAXReader().read(new File("xml/testNamespaces.xml"));
    }
}
