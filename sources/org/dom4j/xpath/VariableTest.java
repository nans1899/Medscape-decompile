package org.dom4j.xpath;

import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.jaxen.SimpleVariableContext;

public class VariableTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {"$author"};
    private Node authorNode;
    private Node rootNode;
    private SimpleVariableContext variableContext = new SimpleVariableContext();

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.VariableTest");
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
        List selectNodes = createXPath(str).selectNodes(this.document);
        StringBuffer stringBuffer = new StringBuffer("Searched path: ");
        stringBuffer.append(str);
        stringBuffer.append(" found: ");
        stringBuffer.append(selectNodes.size());
        stringBuffer.append(" result(s)");
        log(stringBuffer.toString());
        assertTrue("Results should not contain the root node", !selectNodes.contains(this.rootNode));
    }

    /* access modifiers changed from: protected */
    public XPath createXPath(String str) {
        return DocumentHelper.createXPath(str, this.variableContext);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.rootNode = this.document.selectSingleNode("/root");
        this.authorNode = this.document.selectSingleNode("/root/author[1]");
        this.variableContext.setVariableValue("root", this.rootNode);
        this.variableContext.setVariableValue("author", this.authorNode);
    }
}
