package org.dom4j;

import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.tree.DefaultElement;
import org.dom4j.xpath.DefaultXPath;

public class XPathTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected static String[] paths = {".", "*", "/", "/.", "/*", "/node()", "/child::node()", "/self::node()", "root", "/root", "/root/author", "text()", "//author", "//author/text()", "//@location", "//attribute::*", "//namespace::*", "normalize-space(/root)", "//author[@location]", "//author[@location='UK']", "root|author", "//*[.='James Strachan']", "//root/author[1]", "normalize-space(/root/author)", "normalize-space(' a  b  c  d ')", "//root|//author[1]|//author[2]", "//root/author[2]", "//root/author[3]"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.XPathTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testBug1116471() throws Exception {
        Document parseText = DocumentHelper.parseText("<a><b>Water T &amp; D-46816</b></a>");
        assertEquals("xpath result not correct", "Water T & D-46816", (String) parseText.selectObject("string(a/b[1])"));
        assertEquals("xpath result not correct", "Water T & D-46816", parseText.selectSingleNode("a/b").getStringValue());
    }

    public void testXPaths() throws Exception {
        for (String testXPath : paths) {
            testXPath(testXPath);
        }
    }

    public void testCreateXPathBug() throws Exception {
        XPath createXPath = new DefaultElement("foo").createXPath("//bar");
        StringBuffer stringBuffer = new StringBuffer("created a valid XPath: ");
        stringBuffer.append(createXPath);
        assertTrue(stringBuffer.toString() != null);
    }

    public void testBug857704() throws Exception {
        DocumentHelper.parseText("<foo xmlns:bar='http://blort'/>").selectNodes("//*[preceding-sibling::*]");
    }

    public void testBooleanValueOf() throws Exception {
        Document parseText = DocumentHelper.parseText("<root><foo>blah</foo></root>");
        assertTrue(new DefaultXPath("//root").booleanValueOf(parseText));
        assertFalse(new DefaultXPath("//root2").booleanValueOf(parseText));
    }

    /* access modifiers changed from: protected */
    public void testXPath(String str) {
        String str2;
        StringBuffer stringBuffer = new StringBuffer("Searched path: ");
        stringBuffer.append(str);
        log(stringBuffer.toString());
        List selectNodes = DocumentHelper.createXPath(str).selectNodes(this.document);
        if (selectNodes == null) {
            log("null");
        } else {
            log("[");
            int size = selectNodes.size();
            for (int i = 0; i < size; i++) {
                Object obj = selectNodes.get(i);
                if (obj instanceof Node) {
                    str2 = ((Node) obj).asXML();
                } else if (obj != null) {
                    str2 = obj.toString();
                } else {
                    str2 = "null";
                }
                StringBuffer stringBuffer2 = new StringBuffer("    ");
                stringBuffer2.append(str2);
                log(stringBuffer2.toString());
            }
            log("]");
        }
        log("...........................................");
    }
}
